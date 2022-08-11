package com.example.model

import com.example.factory.LatestNewsResponseModelFactory
import com.example.newsapp.model.home.response.LatestNewsResponseModelMapper
import org.junit.Assert
import org.junit.Test

class LatestNewsResponseModelMapperTest {

    @Test
    fun ` 'fromDomainLatestNewsResponse()' 'with domain not equal null' 'return  model same as domain model'`() {
        //arrange
        val latestNewsResponseDomain = LatestNewsResponseModelFactory.generateDataForLatestNewsResponseDomain()

        //act
        val latestNewsResponseModel = LatestNewsResponseModelMapper.mapper.fromDomain(latestNewsResponseDomain)

        //assert
        Assert.assertNotNull(latestNewsResponseModel)
        Assert.assertEquals(latestNewsResponseModel.totalResults, latestNewsResponseDomain.totalResults)
        Assert.assertEquals(latestNewsResponseModel.status, latestNewsResponseDomain.status)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.id, latestNewsResponseDomain.articles?.get(0)?.id)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.source?.id, latestNewsResponseDomain.articles?.get(0)?.source?.id)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.source?.name, latestNewsResponseDomain.articles?.get(0)?.source?.name)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.author, latestNewsResponseDomain.articles?.get(0)?.author)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.url, latestNewsResponseDomain.articles?.get(0)?.url)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.title, latestNewsResponseDomain.articles?.get(0)?.title)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.description, latestNewsResponseDomain.articles?.get(0)?.description)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.publishedAt, latestNewsResponseDomain.articles?.get(0)?.publishedAt)
        Assert.assertEquals(latestNewsResponseModel.articles?.get(0)?.urlToImage, latestNewsResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainLatestNewsResponse()' 'with domain equal null' 'then return model is null'`() {
        //arrange
        val latestNewsResponseDomain = null

        //act
        val latestNewsResponseModel = LatestNewsResponseModelMapper.mapper.fromDomain(latestNewsResponseDomain)

        //assert
        Assert.assertNull(latestNewsResponseModel)
    }
}