package com.leothos.harrypotterbooks.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Book pojo serialized with Moshi
 * Custom object
 * */

@Parcelize
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
    val synopsis: List<String?>? = null,

    var isInCart: Boolean = false,

    var buttonText: String? = null

) : Parcelable