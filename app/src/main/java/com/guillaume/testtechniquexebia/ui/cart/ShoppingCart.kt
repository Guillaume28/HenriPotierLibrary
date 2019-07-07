package com.guillaume.testtechniquexebia.ui.cart

import android.content.Context
import android.widget.Toast
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao

class ShoppingCart(private val booksDao: BookDao) {

    // private val bookDao: BookDao? = null

    fun addBook(book: Book) {
        val cart = getCart()

        //TODO: C'est là le null, mauvaise comparaison d'isbn, il faudrait taper dans le livre sélectionner
        var targetBook = cart.singleOrNull { it.isbn == book.isbn }

        //TODO: If à revoir
        if (targetBook == null) {
            book.quantity++
            book.isInCart = 1
            cart.add(book)
            targetBook = book
        } else {
            targetBook.quantity++
        }
        saveCart(targetBook)
    }

    fun removeBook(book: Book) {
        val cart = getCart()
        var targetBook = cart.singleOrNull { it.isbn == book.isbn }

        if (targetBook != null) {
            if (targetBook.quantity > 0) {
                targetBook.quantity--
                book.isInCart = 0
                cart.remove(targetBook)
                targetBook = book
            } else {
                targetBook.quantity--
            }

            removeFromCart(targetBook)
        }
    }

    private fun saveCart(book: Book) {
        booksDao.addToCart(book)
    }

    private fun removeFromCart(book: Book) {
        booksDao.removeFromCart(book)
    }

    fun getCart(): MutableList<Book> {
        return booksDao.allBooksInShoppingCart
    }

}
