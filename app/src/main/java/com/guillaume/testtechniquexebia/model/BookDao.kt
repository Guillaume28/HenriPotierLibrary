package com.guillaume.testtechniquexebia.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @get:Query("SELECT * FROM book")
    val all: List<Book>

    @Insert
    fun insertAll(vararg books: Book)

    @get:Query("SELECT * FROM book WHERE book.isInCart = 'true'")
    val allBooksInShoppingCart: List<Book>

    @Update
    fun addToCart(vararg book: Book)

    @Update
    fun removeToCart(vararg book: Book)

}