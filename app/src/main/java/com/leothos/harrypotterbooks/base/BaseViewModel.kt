package com.leothos.harrypotterbooks.base

import androidx.lifecycle.ViewModel
import com.leothos.harrypotterbooks.injections.component.DaggerViewModelInjector
import com.leothos.harrypotterbooks.injections.component.ViewModelInjector
import com.leothos.harrypotterbooks.injections.module.NetworkModule
import com.leothos.harrypotterbooks.view_models.BookListViewModel

abstract class BaseViewModel : ViewModel() {

    /**
     * Dagger2 build configuration
     * */
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