package xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.databinding.FragmentChapterSelectorBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters.PageSelectAdapter
import javax.inject.Inject


@AndroidEntryPoint
class ChapterSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var primVM: PrimarilyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?{
        super.onCreate(savedInstanceState)

        val binding = FragmentChapterSelectorBinding.inflate(layoutInflater)

        binding.close.setOnClickListener { dismiss() }

        val onChapterItemClicked : (chapter: Int)->Unit={
            primVM.setPosition(it)
            dismiss()
        }

        binding.drawerList.apply {
            adapter = PageSelectAdapter(primVM.currentBook.value!!.chaptercount!!,onChapterItemClicked)
            layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        }

        return binding.root
    }

}