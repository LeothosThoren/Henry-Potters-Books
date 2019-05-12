package com.leothos.harrypotterbooks.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.databinding.ActivityMainBinding
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.ui.adapter.BookListAdapter
import com.leothos.harrypotterbooks.ui.views.CountDrawable
import com.leothos.harrypotterbooks.utils.BOTTOM_SHEET_MODAL
import com.leothos.harrypotterbooks.view_models.BookListViewModel


class MainActivity : AppCompatActivity() {

    // var
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BookListViewModel
    private var errorSnackbar: Snackbar? = null


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)


        // Init
        configureViewModel()
        displayErrorMessage()
        configureRecyclerView()

        // Fab
        binding.floatingActionButton.setOnClickListener { launchActivity() }
        binding.viewModel = viewModel
    }


    //****************
    // Configuration
    // ***************

    private fun configureViewModel() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel::class.java)
    }

    @SuppressLint("WrongConstant")
    private fun configureRecyclerView() {
        binding.bookListRv.layoutManager = GridLayoutManager(this, 2)

        // Perform click
        viewModel.bookListAdapter.setOnItemClickListener(object : BookListAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Book) {
                when (view.id) {
                    R.id.add_to_cart -> {
                        viewModel.bookCart[data.isbn] = data
                        Snackbar.make(view, "add to cart ${viewModel.bookCart.size}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> openModalSheetFragment(data.synopsis)
                }

            }

        })
    }


    //****************
    // UI
    // ***************

    /**
     * Open a modal bottom sheet
     * */
    private fun openModalSheetFragment(listOfString: List<String?>?) {
        val modal = SynopsisFragment()
        modal.newInstance(listOfString)
            .show(supportFragmentManager, BOTTOM_SHEET_MODAL)
    }

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


    //****************
    // Action
    // ***************

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.cart_item -> launchActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.findItem(R.id.cart_item)
        val icon = menuItem.icon as LayerDrawable
        setBadgeCount(this, viewModel.bookCart.size.toString(), icon)
        invalidateOptionsMenu()
        return super.onPrepareOptionsMenu(menu)
    }


    private fun setBadgeCount(context: Context, count: String, icon: LayerDrawable) {

        val badge: CountDrawable

        // Reuse drawable if possible
        val reuse = icon.findDrawableByLayerId(R.id.ic_cart_count)
        badge = if (reuse != null && reuse is CountDrawable) {
            reuse
        } else {
            CountDrawable(context)
        }

        badge.setCount(count)
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_cart_count, badge)
    }


    private fun launchActivity() {
        if (viewModel.bookCart.size > 0) {
            val intent = Intent(this, OfferActivity::class.java)
            intent.putExtra("ISBN", viewModel.bookCart)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, getString(R.string.alert_user), Toast.LENGTH_LONG).show()
        }

    }
}
