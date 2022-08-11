package com.example.data.source.local.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(objList: T?): Long?

    @Delete
    suspend fun deleteArticle(objList:T?) : Int
}