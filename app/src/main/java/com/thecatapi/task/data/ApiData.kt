package com.thecatapi.task.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiData(
    @Json(name = "id") val catId: String,
    @Json(name = "url") val catImageUrl: String
)
