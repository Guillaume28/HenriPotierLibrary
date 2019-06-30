package com.guillaume.testtechniquexebia.ui.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.guillaume.testtechniquexebia.databinding.StoreItemBookBinding
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.ui.cart.CartListViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_store_list.*
import kotlinx.android.synthetic.main.store_item_book.view.*

class StoreListAdapter : RecyclerView.Adapter<StoreListAdapter.ViewHolder>() {
    private lateinit var booksList: List<Book>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: StoreItemBookBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                com.guillaume.testtechniquexebia.R.layout.store_item_book,
                parent,
                false
            )
        return ViewHolder(binding)
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

    class ViewHolder(private val binding: StoreItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = StoreViewModel()

        fun bind(book: Book) {
            viewModel.bind(book)
            binding.viewModel = viewModel

            lateinit var booksDao: BookDao
            lateinit var cartListViewModel: CartListViewModel

            Observable.create(ObservableOnSubscribe<MutableList<Book>> {

                itemView.addToCart.setOnClickListener { view ->

                    booksDao.addToCart(book)
                    Snackbar.make(
                        (itemView.context as StoreListActivity).activityStoreList,
                        "${book.title} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(cartListViewModel.getCart())

                }

                itemView.removeFromCart.setOnClickListener { view ->

                    booksDao.removeFromCart(book)
                    Snackbar.make(
                        (itemView.context as StoreListActivity).activityStoreList,
                        "${book.title} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()

                    it.onNext(cartListViewModel.getCart())

                }
            }).subscribe { cart ->
                var quantity = 0

                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                }

            }

        }
    }
}
