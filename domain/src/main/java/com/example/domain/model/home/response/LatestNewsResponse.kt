package com.example.domain.model.home.response
import com.example.domain.model.home.Article

data class LatestNewsResponse(
    var status :String ? =null,
    var totalResults : Int ?= null,
    var articles: List<Article>? =null
)