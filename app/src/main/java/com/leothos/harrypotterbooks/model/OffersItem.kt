package com.leothos.harrypotterbooks.model

import com.squareup.moshi.Json

/**
 * Book pojo serialized with Moshi
 * Custom object
 * */
data class OffersItem(

    @Json(name = "sliceValue")
    val sliceValue: Int? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "value")
    val value: Int? = null
)