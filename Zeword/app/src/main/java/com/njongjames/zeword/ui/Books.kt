package com.njongjames.zeword.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.njongjames.zeword.Backend.Utilz
import com.njongjames.zeword.R
import com.njongjames.zeword.Reader
import com.njongjames.zeword.adapters.BooksItemRecyclerViewAdapter
import com.njongjames.zeword.databinding.FragmentBooksBinding
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class Books : Fragment() {

    private var columnCount = 3
    private var tab =0;

    lateinit var binding: FragmentBooksBinding

    @Inject
    lateinit var pmv: PrimarilyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentBooksBinding.inflate(layoutInflater)

        arguments?.let {
            tab = it.getInt(ARG_TAB)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.recyclerView

        pmv = PrimarilyViewModel(utilz = Utilz(view.context))

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = GridLayoutManager(context, columnCount)
                adapter = BooksItemRecyclerViewAdapter( pmv.getBooks(tab)){position ->onListItemClick(position)}
            }
        }
        return binding.root
    }


    private fun onListItemClick(position: Int) {
        var intent : Intent = Intent(context, Reader::class.java)
        intent.putExtra("Position", position)
        intent.putExtra("Tab", tab)
        startActivity(intent)
    }

    companion object {

        const val ARG_TAB = "tab"

        @JvmStatic
        fun newInstance(tab: Int) =
            Books().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TAB, tab)
                }
            }
    }
}