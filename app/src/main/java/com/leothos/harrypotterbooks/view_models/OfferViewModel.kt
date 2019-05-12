package com.leothos.harrypotterbooks.view_models

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.base.BaseViewModel
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.model.Offers
import com.leothos.harrypotterbooks.remote.HenriPotierApi
import com.leothos.harrypotterbooks.ui.adapter.SelectionListAdapter
import com.leothos.harrypotterbooks.utils.computeTotalPrice
import com.leothos.harrypotterbooks.utils.giveBestCommercialOffer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferViewModel : BaseViewModel() {

    @Inject
    lateinit var henriPotierApi: HenriPotierApi

    private lateinit var subscription: Disposable
    // val
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadOffers(isbn.value!!) }
    val showOffer = MutableLiveData<String>()
    val selectionBookListAdapter: SelectionListAdapter = SelectionListAdapter()

    var hashMapOfBooks: HashMap<String, Book> = HashMap()
    val isbn: MutableLiveData<String> = MutableLiveData()
    val total: MutableLiveData<String> = MutableLiveData()


    fun loadOffers(isbn: String) {
        subscription =
            henriPotierApi.getCommercialOffers(isbn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveBookListStart() }
                .doOnTerminate { onRetrieveBookListFinish() }
                .subscribe(
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

    private fun onRetrieveSuccess(offers: Offers) {
        //Update view here
        getOffers(offers)

    }

    private fun onRetrieveError() {
        Log.d(TAG, "Error message")
        errorMessage.value = R.string.book_error
    }

    private fun getOffers(offers: Offers) {
        showOffer.value = (giveBestCommercialOffer(computeTotalPrice(hashMapOfBooks), offers)).toString()
        total.value = computeTotalPrice(hashMapOfBooks).toString()
    }


    /**
     * Exposed viewModel to the views databinding
     * */
    fun getBestOffer(): MutableLiveData<String> {
        return showOffer
    }

    fun getSelectionAdapter(h: HashMap<String, Book>) {
        selectionBookListAdapter.updateSelectionList(h)
    }

    fun getTotalPrice(): MutableLiveData<String> {
        return total
    }


    /**
     * To dispose subscription when the viewModel is no longer used and will be destroyed
     */

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    companion object {
        val TAG = OfferViewModel::class.java.simpleName
    }
}