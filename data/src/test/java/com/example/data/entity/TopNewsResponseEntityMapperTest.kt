package com.example.data.entity

import com.example.data.entity.home.response.TopNewsResponseEntityMapper
import com.example.data.factory.LatestNewsResponseEntityFactory
import com.example.data.factory.TopNewsResponseEntityFactory
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class TopNewsResponseEntityMapperTest {

    @Test
    fun `'toDomainTopNewsResponse()' 'with data model is not null' 'then return domain model same as data model'`() {
        //arrange
        val topNewsResponseEntity = TopNewsResponseEntityFactory.generateDataForTopNewsResponseEntity()

        //act
        val topNewsResponseDomain = TopNewsResponseEntityMapper.mapper.toDomain(topNewsResponseEntity)

        //assert
        Assert.assertNotNull(topNewsResponseDomain)
        assertEquals(topNewsResponseDomain.source, topNewsResponseDomain.source)
        assertEquals(topNewsResponseDomain.sortBy, topNewsResponseDomain.sortBy)
        assertEquals(topNewsResponseDomain.status, topNewsResponseDomain.status)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.id, topNewsResponseDomain.articles?.get(0)?.id)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.source?.id, topNewsResponseDomain.articles?.get(0)?.source?.id)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.source?.name, topNewsResponseDomain.articles?.get(0)?.source?.name)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.author, topNewsResponseDomain.articles?.get(0)?.author)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.url, topNewsResponseDomain.articles?.get(0)?.url)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.title, topNewsResponseDomain.articles?.get(0)?.title)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.description, topNewsResponseDomain.articles?.get(0)?.description)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.publishedAt, topNewsResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(topNewsResponseDomain.articles?.get(0)?.urlToImage, topNewsResponseDomain.articles?.get(0)?.urlToImage)

    }

    @Test
    fun `'toDomainTopNewsResponse()' 'with data model is null' 'then return domain model is null'`() {
        //arrange
        val topNewsResponseEntity = null

        //act
        val topNewsResponseDomain = TopNewsResponseEntityMapper.mapper.toDomain(topNewsResponseEntity)

        //assert
        Assert.assertNull(topNewsResponseDomain)
    }

    @Test
    fun ` 'fromDomainTopNewsResponse()' 'with domain not equal null' 'return data model same as domain model'`() {
        //arrange
        val topNewsResponseDomain = TopNewsResponseEntityFactory.generateDataForTopNewsResponseDomain()

        //act
        val topNewsResponseEntity = TopNewsResponseEntityMapper.mapper.fromDomain(topNewsResponseDomain)

        //assert
        Assert.assertNotNull(topNewsResponseEntity)
        assertEquals(topNewsResponseEntity.source, topNewsResponseDomain.source)
        assertEquals(topNewsResponseEntity.sortBy, topNewsResponseDomain.sortBy)
        assertEquals(topNewsResponseEntity.status, topNewsResponseDomain.status)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.id, topNewsResponseDomain.articles?.get(0)?.id)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.source?.id, topNewsResponseDomain.articles?.get(0)?.source?.id)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.source?.name, topNewsResponseDomain.articles?.get(0)?.source?.name)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.author, topNewsResponseDomain.articles?.get(0)?.author)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.url, topNewsResponseDomain.articles?.get(0)?.url)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.title, topNewsResponseDomain.articles?.get(0)?.title)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.description, topNewsResponseDomain.articles?.get(0)?.description)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.publishedAt, topNewsResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(topNewsResponseEntity.articles?.get(0)?.urlToImage, topNewsResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainTopNewsResponse()' 'with domain equal null' 'then return data model is null'`() {
        //arrange
        val topNewsResponseDomain = null

        //act
        val topNewsResponseEntity = TopNewsResponseEntityMapper.mapper.fromDomain(topNewsResponseDomain)

        //assert
        Assert.assertNull(topNewsResponseEntity)
    }
}