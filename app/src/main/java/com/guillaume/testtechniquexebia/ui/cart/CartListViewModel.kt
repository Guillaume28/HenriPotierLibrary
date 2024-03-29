package com.guillaume.testtechniquexebia.ui.cart

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.R
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.network.BookApi
import io.reactivex.Observable
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

    val cartListAdapter: CartListAdapter =
        CartListAdapter()

    init {
        loadBooksInCart()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadBooksInCart() {
        subscription = Observable.fromCallable { booksDao.allBooksInShoppingCart }
            .concatMap { dbBooksList ->
                if (dbBooksList.isEmpty())
                    bookApi.getBooks().concatMap { apiBooksList -> booksDao.insertAll(*apiBooksList.toTypedArray())
                        Observable.just(apiBooksList)
                    }
                else
                    Observable.just(dbBooksList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveBookListStart() }
            .doOnTerminate { onRetrieveBookListFinish() }
            .subscribe(
                { result -> onRetrieveBookListSuccess(result) },
                { onRetrieveBookListError() }
            )
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

    private fun onRetrieveBookListError() {
        errorMessage.value = R.string.no_book_in_cart
    }

}
