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
        val targetBook = cart.singleOrNull { book.isbn == book.isbn }

        //TODO: If à revoir
        if (targetBook == null) {
            book.quantity++
            cart.add(book)
        } else {
            book.isInCart = true
            targetBook.quantity++
        }
        saveCart(targetBook!!)
    }

    fun removeBook(book: Book, context: Context) {
        val cart = getCart()
        val targetBook = cart.singleOrNull { book.isbn == book.isbn }

        if (targetBook != null) {
            if (targetBook.quantity > 0) {
                Toast.makeText(context, "great quantity", Toast.LENGTH_SHORT).show()
                targetBook.quantity--
            } else {
                book.isInCart = false
                cart.remove(targetBook)
            }
        }
        saveCart(targetBook!!)
    }

    private fun saveCart(book: Book) {
        booksDao.addToCart(book)
    }

    fun getCart(): MutableList<Book> {
        return booksDao.allBooksInShoppingCart
    }

}
