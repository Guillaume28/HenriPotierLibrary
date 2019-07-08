package com.guillaume.testtechniquexebia.injection.component

import com.guillaume.testtechniquexebia.injection.module.NetworkModule
import com.guillaume.testtechniquexebia.ui.cart.ShoppingCart
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ShoppingCartInjector {

    fun inject(shoppingCart: ShoppingCart)

    @Component.Builder
    interface Builder {
        fun build(): ShoppingCartInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}
