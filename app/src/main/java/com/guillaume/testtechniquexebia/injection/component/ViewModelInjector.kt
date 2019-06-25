package com.guillaume.testtechniquexebia.injection.component

import com.guillaume.testtechniquexebia.injection.module.NetworkModule
import com.guillaume.testtechniquexebia.ui.BookListViewModel
import com.guillaume.testtechniquexebia.ui.BookViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified BookListViewModel.
     * @param bookListViewModel BookListViewModel in which to inject the dependencies
     */
    fun inject(bookListViewModel: BookListViewModel)

    fun inject(bookViewModel: BookViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
