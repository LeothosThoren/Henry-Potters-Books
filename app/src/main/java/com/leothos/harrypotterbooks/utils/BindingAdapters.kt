package com.leothos.harrypotterbooks.utils


/**
 * Binding adapter allow the view to be associated to the data using data binding.
 * Live is data is used to observe the of state of the data value.
 * */
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.leothos.harrypotterbooks.utils.extensions.getParentActivity

/**
 * This method handle view visibility like a Progressbar when data is fetching in the internet for example
 * */
@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

/**
 * This method handle TextView value
 * */
@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

/**
 * This adapter is particularly necessary when using third party library as Glide in order to adapt the image view,
 * with the Uri provide from the internet
 * */
@BindingAdapter("mutableImage")
fun setMutableImage(imageView: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = imageView.getParentActivity()
    if (parentActivity != null && url != null) {
        url.observe(
            parentActivity,
            Observer { value -> Glide.with(imageView.context).load(value ?: "").into(imageView) })
    }
}

/**
 * Binding adapter which handle the RecyclerView adapter
 * */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}