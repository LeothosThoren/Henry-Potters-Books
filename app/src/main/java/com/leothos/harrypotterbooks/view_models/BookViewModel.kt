package com.leothos.harrypotterbooks.view_models

import androidx.lifecycle.MutableLiveData
import com.leothos.harrypotterbooks.base.BaseViewModel
import com.leothos.harrypotterbooks.model.Book

class BookViewModel : BaseViewModel() {

    private val bookIsbn = MutableLiveData<String>()
    private val bookTitle = MutableLiveData<String>()
    private val bookCover = MutableLiveData<String>()
    private val bookPrice = MutableLiveData<String>()
//    private val bookSynopsis = MutableLiveData<List<String>>()

    fun bind(book: Book) {
        bookIsbn.value = book.isbn
        bookTitle.value = book.title
        bookCover.value = book.cover
        bookPrice.value = "${book.price} €"
    }

    fun getBookTitle(): MutableLiveData<String> {
        return bookTitle
    }

    fun getBookCover(): MutableLiveData<String> {
        return bookCover
    }

    fun getBookPrice(): MutableLiveData<String> {
        return bookPrice
    }
}
