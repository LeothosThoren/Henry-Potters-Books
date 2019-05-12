package com.leothos.harrypotterbooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leothos.harrypotterbooks.R
import com.leothos.harrypotterbooks.databinding.FragmentSynopsisDialogBinding
import com.leothos.harrypotterbooks.utils.LIST_EXTRA_BUNDLE
import com.leothos.harrypotterbooks.utils.generateStringFromList
import com.leothos.harrypotterbooks.view_models.BookViewModel
import java.util.ArrayList

class SynopsisFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSynopsisDialogBinding
    private lateinit var sharedViewModel: BookViewModel
    private var synopsis: List<String?>? = null

    fun newInstance(list: List<String?>?): SynopsisFragment {
        val synopsisFragment = SynopsisFragment()
        val bundle = Bundle()
        bundle.putStringArrayList(LIST_EXTRA_BUNDLE, list as ArrayList<String?>)
        synopsisFragment.arguments = bundle
        return synopsisFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_synopsis_dialog, container, false)

        val view = binding.root

        // Get the data from bundle
        synopsis = arguments?.getStringArrayList(LIST_EXTRA_BUNDLE)?.toList()

        // Use the viewModel
        activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(BookViewModel::class.java).apply {
                getBookSynopsis().postValue(generateStringFromList(synopsis))
            }
        }

        binding.viewModel = sharedViewModel

        return view
    }

}
