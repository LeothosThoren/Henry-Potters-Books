package com.leothos.harrypotterbooks.view_models

import androidx.lifecycle.MutableLiveData
import com.leothos.harrypotterbooks.base.BaseViewModel
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.utils.generateStringFromList

class BookViewModel : BaseViewModel() {

    private val bookIsbn = MutableLiveData<String>()
    private val bookTitle = MutableLiveData<String>()
    private val bookCover = MutableLiveData<String>()
    private val bookPrice = MutableLiveData<String>()
    private val bookSynopsis = MutableLiveData<String>()

    fun bind(book: Book) {
        bookIsbn.value = book.isbn
        bookTitle.value = book.title
        bookCover.value = book.cover
        bookPrice.value = "${book.price} €"
        bookSynopsis.value = generateStringFromList(book.synopsis)
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

    fun getBookSynopsis(): MutableLiveData<String> {
        return bookSynopsis
    }
}
