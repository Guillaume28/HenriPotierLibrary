package com.guillaume.testtechniquexebia.injection.component

import com.guillaume.testtechniquexebia.injection.module.NetworkModule
import com.guillaume.testtechniquexebia.ui.cart.CartListViewModel
import com.guillaume.testtechniquexebia.ui.cart.CartViewModel
import com.guillaume.testtechniquexebia.ui.store.StoreListViewModel
import com.guillaume.testtechniquexebia.ui.store.StoreViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified CartListViewModel.
     * @param cartListViewModel CartListViewModel in which to inject the dependencies
     */
    fun inject(cartListViewModel: CartListViewModel)

    fun inject(cartViewModel: CartViewModel)

    fun inject(storeListViewModel: StoreListViewModel)

    fun inject(storeViewModel: StoreViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
