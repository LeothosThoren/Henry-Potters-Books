package com.leothos.harrypotterbooks.remote

import com.leothos.harrypotterbooks.model.Book
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *
 * The interface which provides methods to get result of webservices
 */
interface BooksApi {

    /**
     * Get a list of bokks and theirs detail from Xebia APi
     * Call performed with retrofit and RxJava
     */
    @GET("books")
    fun getBooks(): Observable<List<Book>>
}