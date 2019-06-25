package com.guillaume.testtechniquexebia

import androidx.lifecycle.ViewModel
import com.guillaume.testtechniquexebia.injection.component.DaggerViewModelInjector
import com.guillaume.testtechniquexebia.injection.component.ViewModelInjector
import com.guillaume.testtechniquexebia.injection.module.NetworkModule
import com.guillaume.testtechniquexebia.ui.BookListViewModel

abstract class BaseViewModel: ViewModel(){
    /** Dependency Injector */

    private val injector: ViewModelInjector = DaggerViewModelInjector
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
            is BookListViewModel -> injector.inject(this)
        }
    }
}
