package com.example.data.entity

import com.example.data.entity.home.response.LatestNewsResponseEntityMapper
import com.example.data.factory.LatestNewsResponseEntityFactory
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class LatestResponseEntityMapperTest {

    @Test
    fun `'toDomainLatestNewsResponse()' 'with data model is not null' 'then return domain model same as data model'`() {
        //arrange
        val latestNewsResponseEntity = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseEntity()

        //act
        val latestNewsResponseDomain = LatestNewsResponseEntityMapper.mapper.toDomain(latestNewsResponseEntity)

        //assert
        Assert.assertNotNull(latestNewsResponseDomain)
        assertEquals(latestNewsResponseDomain.totalResults, latestNewsResponseDomain.totalResults)
        assertEquals(latestNewsResponseDomain.status, latestNewsResponseDomain.status)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.id, latestNewsResponseDomain.articles?.get(0)?.id)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.source?.id, latestNewsResponseDomain.articles?.get(0)?.source?.id)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.source?.name, latestNewsResponseDomain.articles?.get(0)?.source?.name)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.author, latestNewsResponseDomain.articles?.get(0)?.author)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.url, latestNewsResponseDomain.articles?.get(0)?.url)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.title, latestNewsResponseDomain.articles?.get(0)?.title)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.description, latestNewsResponseDomain.articles?.get(0)?.description)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.publishedAt, latestNewsResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(latestNewsResponseDomain.articles?.get(0)?.urlToImage, latestNewsResponseDomain.articles?.get(0)?.urlToImage)

    }

    @Test
    fun `'toDomainLatestNewsResponse()' 'with data model is null' 'then return domain model is null'`() {
        //arrange
        val latestNewsResponseEntity = null

        //act
        val latestNewsResponseDomain = LatestNewsResponseEntityMapper.mapper.toDomain(latestNewsResponseEntity)

        //assert
        Assert.assertNull(latestNewsResponseDomain)
    }

    @Test
    fun ` 'fromDomainLatestNewsResponse()' 'with domain not equal null' 'return data model same as domain model'`() {
        //arrange
        val latestNewsResponseDomain = LatestNewsResponseEntityFactory.generateDataForLatestNewsNewsResponseDomain()

        //act
        val latestNewsResponseEntity = LatestNewsResponseEntityMapper.mapper.fromDomain(latestNewsResponseDomain)

        //assert
        Assert.assertNotNull(latestNewsResponseEntity)
        assertEquals(latestNewsResponseEntity.totalResults, latestNewsResponseDomain.totalResults)
        assertEquals(latestNewsResponseEntity.status, latestNewsResponseDomain.status)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.id, latestNewsResponseDomain.articles?.get(0)?.id)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.source?.id, latestNewsResponseDomain.articles?.get(0)?.source?.id)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.source?.name, latestNewsResponseDomain.articles?.get(0)?.source?.name)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.author, latestNewsResponseDomain.articles?.get(0)?.author)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.url, latestNewsResponseDomain.articles?.get(0)?.url)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.title, latestNewsResponseDomain.articles?.get(0)?.title)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.description, latestNewsResponseDomain.articles?.get(0)?.description)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.publishedAt, latestNewsResponseDomain.articles?.get(0)?.publishedAt)
        assertEquals(latestNewsResponseEntity.articles?.get(0)?.urlToImage, latestNewsResponseDomain.articles?.get(0)?.urlToImage)
    }

    @Test
    fun `'fromDomainLatestNewsResponse()' 'with domain equal null' 'then return data model is null'`() {
        //arrange
        val latestNewsResponseDomain = null

        //act
        val latestNewsResponseEntity = LatestNewsResponseEntityMapper.mapper.fromDomain(latestNewsResponseDomain)

        //assert
        Assert.assertNull(latestNewsResponseEntity)
    }
}