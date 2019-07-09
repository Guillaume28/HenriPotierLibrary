package com.guillaume.testtechniquexebia.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.guillaume.testtechniquexebia.database.AppDatabase
import com.guillaume.testtechniquexebia.ui.cart.CartListViewModel
import com.guillaume.testtechniquexebia.ui.store.StoreListViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "books")
                .allowMainThreadQueries().build()
            @Suppress("UNCHECKED_CAST")
            return CartListViewModel(db.booksDao()) as T
        }

        if (modelClass.isAssignableFrom(StoreListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "books")
                .allowMainThreadQueries().build()
            @Suppress("UNCHECKED_CAST")
            return StoreListViewModel(db.booksDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
