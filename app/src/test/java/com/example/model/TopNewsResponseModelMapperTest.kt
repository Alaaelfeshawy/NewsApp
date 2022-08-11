package com.example.model

import com.example.factory.TopNewsResponseModelFactory
import com.example.newsapp.model.home.response.TopNewsResponseModelMapper
import org.junit.Assert
import org.junit.Test

class TopNewsResponseModelMapperTest {

    @Test
    fun ` 'fromDomainTopNewsResponse()' 'with domain not equal null' 'return  model same as domain model'`() {
        //arrange
        val topNewsResponseDomain = TopNewsResponseModelFactory.generateDataForTopNewsResponseDomain()

        //act
        val topNewsResponseModel = TopNewsResponseModelMapper.mapper.fromDomain(topNewsResponseDomain)

        //assert
        Assert.assertNotNull(topNewsResponseModel)
        Assert.assertEquals(topNewsResponseModel.source, topNewsResponseDomain.source)
        Assert.assertEquals(topNewsResponseModel.sortBy, topNewsResponseDomain.sortBy)
        Assert.assertEquals(topNewsResponseModel.status, topNewsResponseDomain.status)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.id, topNewsResponseDomain.articles?.get(0)?.id)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.source?.id, topNewsResponseDomain.articles?.get(0)?.source?.id)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.source?.name, topNewsResponseDomain.articles?.get(0)?.source?.name)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.author, topNewsResponseDomain.articles?.get(0)?.author)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.url, topNewsResponseDomain.articles?.get(0)?.url)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.title, topNewsResponseDomain.articles?.get(0)?.title)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.description, topNewsResponseDomain.articles?.get(0)?.description)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.publishedAt, topNewsResponseDomain.articles?.get(0)?.publishedAt)
        Assert.assertEquals(topNewsResponseModel.articles?.get(0)?.urlToImage, topNewsResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainTopNewsResponse()' 'with domain equal null' 'then return model is null'`() {
        //arrange
        val topNewsResponseDomain = null

        //act
        val topNewsResponseModel = TopNewsResponseModelMapper.mapper.fromDomain(topNewsResponseDomain)

        //assert
        Assert.assertNull(topNewsResponseModel)
    }
}