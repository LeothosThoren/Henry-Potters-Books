package com.leothos.harrypotterbooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.databinding.ItemBookSelectionBinding
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.view_models.BookViewModel

class SelectionListAdapter : RecyclerView.Adapter<SelectionListAdapter.ViewHolder>() {

    private lateinit var bookList: List<Book>
//    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        setOnItemClickListener(listener)
        val binding: ItemBookSelectionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book_selection, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::bookList.isInitialized) bookList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookData = bookList[position]
        holder.bind(bookData)
//        holder.itemView.setOnClickListener { listener.onClick(it, bookData) }

    }

    /**
     * UI handler
     * */

    fun updateSelectionList(selectionList: HashMap<String, Book>) {
        this.bookList = selectionList.values.toList()
        notifyDataSetChanged()
    }

    /**
     * Click handler
     * */

//    interface OnItemClickListener {
//        fun onClick(view: View, data: Book)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }


    /**
     * ViewHolder class to hold and bind items
     * */
    class ViewHolder(
        private val binding: ItemBookSelectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = BookViewModel()

        fun bind(book: Book) {
            viewModel.bind(book)
            binding.viewModel = viewModel
        }
    }
}