package com.guillaume.testtechniquexebia.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @get:Query("SELECT * FROM book")
    val all: List<Book>

    @Insert
    fun insertAll(vararg books: Book)
}