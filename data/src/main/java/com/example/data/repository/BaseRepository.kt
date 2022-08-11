package com.example.data.repository

open class BaseRepository(private val fromTest: Boolean = false) {
    val apiToken: String
        get() = if (!fromTest){
            "790cef2adfad4889ad2c7d3344a25964"
        }else{
            "apiKey"
        }
}