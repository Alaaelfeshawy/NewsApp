package com.example.data.factory

import com.example.data.entity.home.SourceEntity
import com.example.domain.model.home.Source


object SourceEntityFactory {
    const val NUM_ATTRIBUTES=2

    fun generateDataForSourceEntity(): SourceEntity
    {
        return SourceEntity(
            "id",
            "name",
        )
    }

    fun generateDataForSourceDomain(): Source {
        return Source(
            "id",
            "name",
        )
    }
}