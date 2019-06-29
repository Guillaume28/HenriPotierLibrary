package com.guillaume.testtechniquexebia.ui.store

import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.model.Book

class StoreViewModel : BaseViewModel() {

    private val bookTitle = MutableLiveData<String>()
    private val bookPrice = MutableLiveData<Int>()
    private val bookCover = MutableLiveData<String>()

    fun bind(book: Book){
        bookCover.value = book.cover
        bookTitle.value = book.title
        bookPrice.value = book.price
    }

    fun getBookTitle():MutableLiveData<String>{
        return bookTitle
    }

    fun getBookPrice():MutableLiveData<Int>{
        return bookPrice
    }

    fun getBookCover(): MutableLiveData<String>{
        return bookCover
    }
}
