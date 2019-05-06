package com.leothos.harrypotterbooks.view_models

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.base.BaseViewModel
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.remote.BooksApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookListViewModel : BaseViewModel() {
    companion object {
        val TAG = BookListViewModel::class.java.simpleName
    }

    @Inject
    lateinit var booksApi: BooksApi

    private lateinit var subscription: Disposable

    // val
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadBookList() }

    init {
        loadBookList()
    }

    private fun loadBookList() {
        subscription = booksApi.getBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveBookListStart() }
            .doOnTerminate { onRetrieveBookListFinish() }
            .subscribe(
                // Add result
                { result ->
                    onRetrieveSuccess(result)
                },

                {
                    onRetrieveError()
                }
            )
    }

    /**
     * Add a MutableLiveData to the view that will be able to observe in order to update
     * the visibility of the ProgressBar we will show while retrieving the data from the API.
     * */

    private fun onRetrieveBookListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveBookListFinish() {
        loadingVisibility.value = View.GONE
    }

    /**
     * Handle the network behavior on success or on error
     *
     * */

    private fun onRetrieveSuccess(book: List<Book>) {
        Log.d(TAG, "List of books = ${book.listIterator().next()}")
        //Update recycler view here
//        .updateRssList(book)
    }

    private fun onRetrieveError() {
        Log.d(TAG, "Error message")
        errorMessage.value = R.string.book_error
    }


    /**
     * To dispose subscription when the viewModel is no longer used and will be destroyed
     */

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}