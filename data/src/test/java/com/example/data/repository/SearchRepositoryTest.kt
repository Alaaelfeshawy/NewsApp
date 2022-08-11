package com.example.data.repository


import com.example.data.entity.home.response.LatestNewsResponseEntity
import com.example.data.entity.home.response.TopNewsResponseEntity
import com.example.data.factory.LatestNewsResponseEntityFactory
import com.example.data.factory.TopNewsResponseEntityFactory
import com.example.data.source.remote.HomeApi
import com.example.data.source.remote.SearchApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SearchRepositoryTest {

    private lateinit var SUT: SearchRepository

    @Mock
    private lateinit var searchApi: SearchApi


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        SUT = SearchRepository(searchApi , true)
    }

    @Test
    fun `'search()' 'with query and api key' 'then check source and api key passed to endpoint'`() {

        runBlocking {
            //arrange
            val cp = ArgumentCaptor.forClass(String::class.java)
            val searchResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()
            val query = "query"
            val apiKey = "apiKey"
            //act
            returnSearchResponse(searchResponseEntity , query , apiKey)
            SUT.search(query)

            //assert
            Mockito.verify(searchApi, Mockito.times(1)).search(cp.capture() ?: "", cp.capture() ?: "")

            val captures = cp.allValues
            assertEquals(captures[0], query)
            assertEquals(captures[1], apiKey)
        }
    }

    @Test
    fun `'search()' 'with apiKey and query ' then return search response '`() {
        // Arrange
        val latestNewsResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()
        val latestNewsResponseDomain = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseDomain()
        val query = "query"
        val apiKey = "apiKey"

       runBlocking {
           // Act
           returnSearchResponse(latestNewsResponseEntity,query, apiKey)
           val testObserver = SUT.search(query)

           // Assert
           Mockito.verify(searchApi, Mockito.times(1)).search(query, apiKey)
           assertEquals(testObserver,latestNewsResponseDomain)
       }
    }


    private fun returnSearchResponse(
        response: LatestNewsResponseEntity,
        query: String,
        apiKey:String
    ) {
       runBlocking {
           Mockito.`when`(searchApi.search(query,apiKey))
               .thenReturn(response)
       }
    }

}