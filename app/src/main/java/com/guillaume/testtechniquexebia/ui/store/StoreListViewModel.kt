package com.guillaume.testtechniquexebia.ui.store

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.guillaume.testtechniquexebia.BaseViewModel
import com.guillaume.testtechniquexebia.R
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.network.BookApi
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoreListViewModel(private val booksDao: BookDao) : BaseViewModel() {

    @Inject
    lateinit var bookApi: BookApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadBooks() }

    val storeListAdapter: StoreListAdapter =
        StoreListAdapter()

    init {
        loadBooks()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadBooks() {
        subscription = Observable.fromCallable { booksDao.all }
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
                { err -> onRetrieveBookListError(err) }
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
        storeListAdapter.updateBooksList(bookList)
    }

    private fun onRetrieveBookListError(err: Throwable) {
        println(err)
        errorMessage.value = R.string.post_error
    }
}
