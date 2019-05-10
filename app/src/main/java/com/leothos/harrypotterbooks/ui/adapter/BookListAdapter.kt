package com.leothos.harrypotterbooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.databinding.ItemBookBinding
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.view_models.BookViewModel
import kotlinx.android.synthetic.main.item_book.view.*

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private lateinit var bookList: List<Book>
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        setOnItemClickListener(listener)
        val binding: ItemBookBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::bookList.isInitialized) bookList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookData = bookList[position]
        holder.bind(bookData)
        holder.itemView.setOnClickListener { listener.onClick(it, bookData) }
        holder.itemView.add_to_cart.setOnClickListener { listener.onClick(it, bookData) }

    }

    /**
     * UI handler
     * */

    fun updateBookList(bookList: List<Book>) {
        this.bookList = bookList
        notifyDataSetChanged()
    }

    /**
     * Click handler
     * */

    interface OnItemClickListener {
        fun onClick(view: View, data: Book)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    /**
     * ViewHolder class to hold and bind items
     * */
    class ViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = BookViewModel()

        fun bind(book: Book) {
            viewModel.bind(book)
            binding.viewModel = viewModel
        }
    }
}