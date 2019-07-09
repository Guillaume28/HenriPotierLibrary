package com.guillaume.testtechniquexebia.network

import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.CommercialOffers
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("/books")
    fun getBooks(): Observable<List<Book>>

    @GET("/books/{isbn}/commercialOffers")
    fun getCommercialOffers(@Path("isbn") isbn: String): CommercialOffers
}
