package com.example.data.source.local.room.entity.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.room.entity.ArticleDB
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

@Entity(tableName = "articles")
open class ArticleData(
//    @field:PrimaryKey(autoGenerate = true)
                       var id : Long? =null,
                       var author: String?= null,
                       @field:PrimaryKey
                       var title: String,
                       var description : String? = null,
                       var url: String? = null,
                       var urlToImage: String? = null,
                       var publishedAt: String?  = null,
                       var saved : Boolean?=false
)

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
interface ArticleDataMapper {
    fun fromDomainList(model: List<ArticleData>?): List<ArticleDB>?

    companion object {
        var mapper: ArticleDataMapper =
            Mappers.getMapper(ArticleDataMapper::class.java)
    }
}