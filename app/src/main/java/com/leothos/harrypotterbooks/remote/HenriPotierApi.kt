package com.leothos.harrypotterbooks.remote

import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.model.Offers
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * The interface which provides methods to get result of webservices
 */
interface HenriPotierApi {

    /**
     * Get a list of books and theirs detail from Xebia APi
     * Call performed with retrofit and RxJava
     */
    @GET("/books")
    fun getBooks(): Observable<List<Book>>

    /**
     * Get a list of books and theirs detail from Xebia APi
     * Call performed with retrofit and RxJava
     */
    @GET("/books/{isbn}/commercialOffers")
    fun getCommercialOffers(@Path("isbn") isbn: String): Observable<Offers>
}