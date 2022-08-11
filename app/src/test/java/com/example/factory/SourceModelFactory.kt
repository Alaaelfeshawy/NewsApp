package com.example.factory

import com.example.domain.model.home.Source
import com.example.newsapp.model.home.SourceModel

object SourceModelFactory {
    const val NUM_ATTRIBUTES=2

    fun generateDataForSourceModel(): SourceModel {
        return SourceModel(
            "1",
            "name",
        )
    }

    fun generateDataForSourceDomain(): Source {
        return Source(
            "1",
            "name",
        )
    }
}