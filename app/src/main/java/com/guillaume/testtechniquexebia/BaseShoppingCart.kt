package com.guillaume.testtechniquexebia

import com.guillaume.testtechniquexebia.injection.component.DaggerShoppingCartInjector
import com.guillaume.testtechniquexebia.injection.component.ShoppingCartInjector
import com.guillaume.testtechniquexebia.injection.module.NetworkModule
import com.guillaume.testtechniquexebia.ui.cart.ShoppingCart

abstract class BaseShoppingCart {
    /** Dependency Injector */

    private val injector: ShoppingCartInjector = DaggerShoppingCartInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ShoppingCart -> injector.inject(this)
        }
    }
}
