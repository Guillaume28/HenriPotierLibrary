package com.guillaume.testtechniquexebia.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.guillaume.testtechniquexebia.R
import com.guillaume.testtechniquexebia.databinding.CartItemBookBinding
import com.guillaume.testtechniquexebia.model.Book


class CartListAdapter : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private lateinit var booksList: List<Book>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CartItemBookBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.cart_item_book, parent, false)
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

    class ViewHolder(private val binding: CartItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CartViewModel()

        fun bind(book: Book) {
            viewModel.bind(book)
            binding.viewModel = viewModel
        }
    }
}
