package com.example.data.repository


import com.example.data.entity.home.response.LatestNewsResponseEntity
import com.example.data.entity.home.response.TopNewsResponseEntity
import com.example.data.factory.LatestNewsResponseEntityFactory
import com.example.data.factory.TopNewsResponseEntityFactory
import com.example.data.source.remote.HomeApi
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
class HomeRepositoryTest {

    private lateinit var SUT: HomeRepository

    @Mock
    private lateinit var homeApi: HomeApi


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        SUT = HomeRepository(homeApi , homeApi , true)
    }

    @Test
    fun `'latestNews()' 'with source and api key' 'then check source and api key passed to endpoint'`() {

        runBlocking {
            //arrange
            val cp = ArgumentCaptor.forClass(String::class.java)
            val homeResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()
            val source = "source"
            val apiKey = "apiKey"
            //act
            returnLatestNewsResponse(homeResponseEntity , source , apiKey)
            SUT.getLatestNews(source)

            //assert
            Mockito.verify(homeApi, Mockito.times(1)).getLatestNews(cp.capture() ?: "", cp.capture() ?: "")

            val captures = cp.allValues
            assertEquals(captures[0], source)
            assertEquals(captures[1], apiKey)
        }
    }

    @Test
    fun `'latestNews()' 'with apiKey and source ' then return latest news response '`() {
        // Arrange
        val latestNewsResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()
        val latestNewsResponseDomain = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseDomain()
        val source = "source"
        val apiKey = "apiKey"

       runBlocking {
           // Act
           returnLatestNewsResponse(latestNewsResponseEntity,source, apiKey)
           val testObserver = SUT.getLatestNews(source)

           // Assert
           Mockito.verify(homeApi, Mockito.times(1)).getLatestNews(source, apiKey)
           assertEquals(testObserver,latestNewsResponseDomain)
       }
    }

    @Test
    fun `'topNews()' 'with country and api key' 'then check country and api key passed to endpoint'`() {

        runBlocking {
            //arrange
            val cp = ArgumentCaptor.forClass(String::class.java)
            val topNewsResponseEntity = TopNewsResponseEntityFactory.generateDataForTopNewsResponseEntity()
            val country = "country"
            val apiKey = "apiKey"
            //act
            returnTopNewsResponse(topNewsResponseEntity , country , apiKey)
            SUT.getTopNews(country)

            //assert
            Mockito.verify(homeApi, Mockito.times(1)).getTopNews(cp.capture() ?: "", cp.capture() ?: "")

            val captures = cp.allValues
            assertEquals(captures[0], country)
            assertEquals(captures[1], apiKey)
        }
    }

    @Test
    fun `'topNews()' 'with apiKey and country ' then return top news response '`() {
        // Arrange
        val topNewsResponseEntity = TopNewsResponseEntityFactory.generateDataForTopNewsResponseEntity()
        val topNewsResponseDomain = TopNewsResponseEntityFactory.generateDataForTopNewsResponseDomain()
        val country = "country"
        val apiKey = "apiKey"

       runBlocking {
           // Act
           returnTopNewsResponse(topNewsResponseEntity,country, apiKey)
           val testObserver = SUT.getTopNews(country)

           // Assert
           Mockito.verify(homeApi, Mockito.times(1)).getTopNews(country, apiKey)
           assertEquals(testObserver,topNewsResponseDomain)
       }
    }

    private fun returnLatestNewsResponse(
        response: LatestNewsResponseEntity,
        source: String,
        apiKey:String
    ) {
       runBlocking {
           Mockito.`when`(homeApi.getLatestNews(source,apiKey))
               .thenReturn(response)
       }
    }

    private fun returnTopNewsResponse(
        response: TopNewsResponseEntity,
        country: String,
        apiKey:String
    ) {
       runBlocking {
           Mockito.`when`(homeApi.getTopNews(country,apiKey))
               .thenReturn(response)
       }
    }

}