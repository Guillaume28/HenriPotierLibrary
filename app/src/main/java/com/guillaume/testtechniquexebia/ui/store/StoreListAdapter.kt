package com.guillaume.testtechniquexebia.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.guillaume.testtechniquexebia.databinding.StoreItemBookBinding
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.ui.cart.ShoppingCart
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_store_list.*
import kotlinx.android.synthetic.main.store_item_book.view.*

class StoreListAdapter(private val bookDao: BookDao) : RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {
    private lateinit var booksList: List<Book>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: StoreItemBookBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                com.guillaume.testtechniquexebia.R.layout.store_item_book,
                parent,
                false
            )
        return ViewHolder(binding, bookDao)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(booksList[position])
    }

    override fun getItemCount(): Int {
        return if (::booksList.isInitialized) booksList.size else 0
    }

    fun updateBooksList(bookList: List<Book>) {
        this.booksList = bookList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: StoreItemBookBinding, private val bookDao: BookDao) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = StoreViewModel()

        fun bind(book: Book) {
            viewModel.bind(book)
            binding.viewModel = viewModel

            Observable.create(ObservableOnSubscribe<MutableList<Book>> {

                itemView.addToCart.setOnClickListener { view ->

                    // ShoppingCart.addBook(book)
                    ShoppingCart(bookDao).addBook(book)
                    Snackbar.make(
                        (itemView.context as StoreListActivity).activityStoreList,
                        "${book.title} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    // it.onNext(ShoppingCart.getCart())
                    it.onNext(ShoppingCart(bookDao).getCart())

                }

                itemView.removeFromCart.setOnClickListener { view ->

                    // ShoppingCart.removeBook(book, itemView.context)
                    ShoppingCart(bookDao).removeBook(book, itemView.context)
                    Snackbar.make(
                        (itemView.context as StoreListActivity).activityStoreList,
                        "${book.title} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    // it.onNext(ShoppingCart.getCart())
                    it.onNext(ShoppingCart(bookDao).getCart())
                }
            })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { cart ->
                    var quantity = 0

                    cart.forEach { cartBook ->
                        quantity += cartBook.quantity
                    }

                }

        }
    }
}
