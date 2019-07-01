package com.guillaume.testtechniquexebia.ui.cart

import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.model.Book

class CartViewModel : BaseViewModel() {

    private val bookTitle = MutableLiveData<String>()
    private val bookPrice = MutableLiveData<Int>()
    private val bookCover = MutableLiveData<String>()

    fun bind(book: Book){
        bookTitle.value = book.title
        bookPrice.value = book.price
        bookCover.value = book.cover
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
