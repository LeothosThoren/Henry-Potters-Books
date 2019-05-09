package com.leothos.harrypotterbooks.model

import com.squareup.moshi.Json

/**
 * Book pojo serialized with Moshi
 * Custom object
 * */
data class Offers(

    @Json(name = "offers")
    val offers: List<OffersItem?>? = null
)