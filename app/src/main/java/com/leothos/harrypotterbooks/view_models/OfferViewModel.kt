package com.leothos.harrypotterbooks.view_models

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.base.BaseViewModel
import com.leothos.harrypotterbooks.model.Offers
import com.leothos.harrypotterbooks.remote.HenriPotierApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OfferViewModel : BaseViewModel() {

    @Inject
    lateinit var henriPotierApi: HenriPotierApi

    private lateinit var subscription: Disposable
    // var
    // val
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadOffers() }
    private val test = MutableLiveData<String>()

    init {
        loadOffers()
    }


    private fun loadOffers() {
        subscription =
            henriPotierApi.getCommercialOffers("c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861")
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
        test.value = offers.offers?.get(0)?.type
    }

    fun getTest(): MutableLiveData<String> {
        return test
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