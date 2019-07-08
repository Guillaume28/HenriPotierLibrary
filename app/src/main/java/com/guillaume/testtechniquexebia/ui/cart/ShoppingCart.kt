package com.guillaume.testtechniquexebia.ui.cart

import com.guillaume.testtechniquexebia.BaseShoppingCart
import com.guillaume.testtechniquexebia.model.Book
import com.guillaume.testtechniquexebia.model.BookDao
import com.guillaume.testtechniquexebia.model.CommercialOffer
import com.guillaume.testtechniquexebia.model.Type
import com.guillaume.testtechniquexebia.network.BookApi
import javax.inject.Inject

class ShoppingCart(private val booksDao: BookDao) : BaseShoppingCart() {

    @Inject
    lateinit var bookApi: BookApi

    fun addBook(book: Book) {
        val cart = getCart()

        var targetBook = cart.singleOrNull { it.isbn == book.isbn }

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

    /*
    fun getBooksDiscount(): Float {
        val cart = getCart()
        val price = cart.fold(0) { sum, book -> sum + book.price }
        val commercialOffers = bookApi.getCommercialOffers(cart.joinToString { it.isbn })


        val total =

        return total
    }
    */

    fun getBestOffer(amount: Float, offers: List<CommercialOffer>): Float? {
        return offers.map { computeDiscount(amount, it) }.min()
    }

    fun computeDiscount(amount: Float, offer: CommercialOffer): Float =
        when (offer.type) {
            Type.MINUS -> offer.value
            Type.PERCENTAGE -> amount * offer.value / 100
            Type.SLICE -> (amount / offer.sliceValue).toInt() * offer.value
            else -> 0F
        }

}
