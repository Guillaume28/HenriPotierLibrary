package com.guillaume.testtechniquexebia.network

import com.guillaume.testtechniquexebia.model.Book
import io.reactivex.Observable
import retrofit2.http.GET

interface BookApi {

    @GET("/books")
    fun getBooks(): Observable<List<Book>>

}
