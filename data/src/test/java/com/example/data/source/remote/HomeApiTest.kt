package com.example.data.source.remote

import com.example.data.factory.LatestNewsResponseEntityFactory
import com.example.data.factory.TopNewsResponseEntityFactory
import com.example.data.utils.TestUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class HomeApiTest {
    private lateinit var SUT: HomeApi

    private lateinit var moshi: Moshi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiKey: String
    private lateinit var source: String
    private lateinit var country: String

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        SUT = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(HomeApi::class.java)

        apiKey = "apiKey"
        source = "source"
        country = "country"
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getLatestNews() 'with apiKey and source' 'then return getLatestNews response'`() {

        //arrange
        val latestNewsResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()
        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("latest_news_response.json"))

        runBlocking {
            // act
            mockWebServer.enqueue(mockResponse)
            val testObserver = SUT.getLatestNews(source, apiKey)
            // assert
            assertNotNull(testObserver)
            assertEquals(testObserver,latestNewsResponseEntity)
        }

    }

    @Test
    fun `getTopNews() 'with apiKey and source' 'then return getTopNews response'`() {

        //arrange
        val topNewsResponseEntity = TopNewsResponseEntityFactory.generateDataForTopNewsResponseEntity()
        // Mock a response with status 200 and sample JSON output
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(TestUtils.getJson("top_news_response.json"))

        runBlocking {
            // act
            mockWebServer.enqueue(mockResponse)
            val testObserver = SUT.getTopNews(country, apiKey)
            // assert
            assertNotNull(testObserver)
            assertEquals(testObserver,topNewsResponseEntity)
        }

    }


}