package com.example.domain.model.home.response
import com.example.domain.model.home.Article

data class TopNewsResponse(
    var status :String ? =null,
    var source : String ?= null,
    var sortByLString: String ? =null,
    var articles: List<Article>? =null
)