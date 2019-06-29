package com.guillaume.testtechniquexebia.ui.store

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.model.Book
import com.squareup.picasso.Picasso

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

    @BindingAdapter("imageUrl", "picasso")
    fun setImageUrl(view: ImageView, poserPath: String, picasso: Picasso) {
        Picasso.with(view.context).load(poserPath).into(view)
    }
}
