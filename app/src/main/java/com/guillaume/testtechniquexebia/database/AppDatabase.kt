package com.guillaume.testtechniquexebia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun booksDao(): BookDao
}
