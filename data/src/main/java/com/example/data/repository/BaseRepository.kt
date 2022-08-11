package com.example.data.repository

open class BaseRepository(private val fromTest: Boolean = false) {
    val apiToken: String
        get() = if (!fromTest){
            "65fbbd52f7b940e5ab6905bd467410f6"
        }else{
            "apiKey"
        }
}