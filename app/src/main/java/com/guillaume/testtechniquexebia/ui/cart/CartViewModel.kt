package com.guillaume.testtechniquexebia.ui.cart

import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.model.Book

class CartViewModel : BaseViewModel() {

    private val bookTitle = MutableLiveData<String>()
    private val bookPrice = MutableLiveData<Int>()
    // private val bookSynopsis = MutableLiveData<Array<String>>()

    fun bind(book: Book){
        bookTitle.value = book.title
        bookPrice.value = book.price
        /**
        bookSynopsis.value = book.synopsis
        */
    }

    fun getBookTitle():MutableLiveData<String>{
        return bookTitle
    }

    fun getBookPrice():MutableLiveData<Int>{
        return bookPrice
    }

    /**
    fun getBookSynopsis():MutableLiveData<Array<String>>{
        return bookSynopsis
    }
    */

}
