package com.guillaume.testtechniquexebia.ui.cart

import android.content.Context
import android.widget.Toast
import com.guillaume.testtechniquexebia.model.Book

class ShoppingCart {

    companion object {
        fun addItem(book: Book) {
            val cart = getCart()
            val targetItem = cart.singleOrNull { book.isbn == book.isbn }

            if (targetItem == null) {
                book.quantity++
                cart.add(book)
            } else {
                targetItem.quantity++
            }
            saveCart(cart)
        }

        fun removeItem(book: Book, context: Context) {
            val cart = getCart()
            val targetItem = cart.singleOrNull { book.isbn == book.isbn }

            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    Toast.makeText(context, "great quantity", Toast.LENGTH_SHORT).show()
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }
            }

            saveCart(cart)
        }

        fun saveCart(cart: MutableList<Book>) {
            // Write in room
        }

        fun getCart(): MutableList<Book> {
            // return from room
        }

    }

}
