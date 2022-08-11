package com.example.data.repository

open class BaseRepository(private val fromTest: Boolean = false) {
    val apiToken: String
        get() = if (!fromTest){
            "a64f915775f94d66865ee7bd6e0f322f"
        }else{
            "apiKey"
        }
}