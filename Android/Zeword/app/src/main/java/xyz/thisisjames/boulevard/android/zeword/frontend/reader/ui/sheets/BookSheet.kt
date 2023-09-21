package xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Bookz
import xyz.thisisjames.boulevard.android.zeword.databinding.NavHeaderReaderBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters.BookListAdapter
import javax.inject.Inject


@AndroidEntryPoint
class BookSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var primVM: PrimarilyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = NavHeaderReaderBinding.inflate(layoutInflater)

        binding.groupname.text = primVM.currentGroup
        binding.bookCount.text = "${primVM.currentGroupSize} Books"

        val onBookItemClicked : (book: Bookz)->Unit={
            primVM.setBook(it)
            dismiss()
        }

        binding.drawerList.apply {
            adapter = BookListAdapter( primVM.focusGroup, onBookItemClicked )
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }

        binding.close.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

}