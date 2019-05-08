package com.leothos.harrypotterbooks.model

import com.squareup.moshi.Json

/**
 * Book pojo serialized with Moshi
 * Custom object
 * */

data class Book(

    @Json(name = "isbn")
    val isbn: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "cover")
    val cover: String? = null,

    @Json(name = "price")
    val price: Int? = null,

    @Json(name = "synopsis")
    val synopsis: List<String?>? = null

)