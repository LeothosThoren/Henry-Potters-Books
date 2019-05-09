package com.leothos.harrypotterbooks.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.databinding.ActivityOfferBinding
import com.leothos.harrypotterbooks.view_models.OfferViewModel


class OfferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOfferBinding
    private lateinit var viewModel: OfferViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_offer)

        configureViewModel()
        displayErrorMessage()

//        viewModel.getTest().observe(this, Observer { binding.testView.text = it })

        binding.offerViewModel = viewModel

    }

    //****************
    // Configuration
    // ***************

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(OfferViewModel::class.java)
    }

    //****************
    // UI
    // ***************

    /**
     * Observe the value of errorMessage in this Activity to display the SnackBar when it is not null,
     * and hide it when the value is null
     * */
    private fun displayErrorMessage() {
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

    }

    /**
     * Show a SnackBar in cas of internet error
     * @param errorMessage value which come from the viewModel
     * */
    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    /**
     * Hide the snack bar if the api call is a success
     * */
    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}
