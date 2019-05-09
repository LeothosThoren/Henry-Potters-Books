package com.leothos.harrypotterbooks.injections.component

import com.leothos.harrypotterbooks.injections.module.NetworkModule
import com.leothos.harrypotterbooks.view_models.BookListViewModel
import com.leothos.harrypotterbooks.view_models.OfferViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters / controllers.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified bookListViewModel.
     * @param bookListViewModel bookListViewModel in which to inject the dependencies
     */
    fun inject(bookListViewModel: BookListViewModel)


    /**
     * Injects required dependencies into the specified bookListViewModel.
     * @param offerViewModel offerViewModel in which to inject the dependencies
     */
    fun inject(offerViewModel: OfferViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}