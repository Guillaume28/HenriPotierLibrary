package com.guillaume.testtechniquexebia.ui.cart

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.R
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.network.BookApi
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CartListViewModel(private val booksDao: BookDao) : BaseViewModel() {

    @Inject
    lateinit var bookApi: BookApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { insertBooksInCart() }

    val cartListAdapter: CartListAdapter =
        CartListAdapter()

    init {
        insertBooksInCart()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun insertBooksInCart() {

    }



    private fun onRetrieveBookListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveBookListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveBookListSuccess(bookList: List<Book>) {
        cartListAdapter.updateBooksList(bookList)
    }

    private fun onRetrieveBookListError(err: Throwable) {
        println(err)
        errorMessage.value = R.string.post_error
    }

    fun getCart(): MutableList<Book> {
        return booksDao.allBooksInShoppingCart
    }

}
