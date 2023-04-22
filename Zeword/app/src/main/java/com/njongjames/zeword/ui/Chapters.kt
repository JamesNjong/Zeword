package com.njongjames.zeword.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.njongjames.zeword.Models.ClickedVerse
import com.njongjames.zeword.adapters.ChapterVerseitemRecyclerViewAdapter
import com.njongjames.zeword.databinding.FragmentChaptersBinding
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class Chapters : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentChaptersBinding? = null



    private var versesPicked=ArrayList<Int>()

    @Inject
    lateinit var pvm: PrimarilyViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var verseSelectables = false

    lateinit var chapterKey: String
    lateinit var bookName:String
    var sectionNumber: Int =0


    lateinit var SelectorCard: CardView
    lateinit var readerbtnclear: ImageButton
    lateinit var btnDelete:ImageButton
    lateinit var readerBtnProcess:ImageButton
    lateinit var actionCard:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chapterKey = arguments?.getString(ARG_CHAPTER_KEY) !!
        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) !!
        bookName = arguments?.getString(ARG_BOOK_NAME)!!
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(chapterKey,verseSelectables)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChaptersBinding.inflate(inflater, container, false)
        val root = binding.root

        SelectorCard = binding.selectorCard
        readerbtnclear = binding.readerBtnClear
        btnDelete = binding.readerBtnDelete
        actionCard = binding.selectorCardview2
        readerBtnProcess = binding.readerBtnProcess

        val recyclerView: RecyclerView = binding.chapterList

        SelectorCard  = binding.selectorCard
        readerbtnclear.setOnClickListener{
            SelectorCard.isVisible = false
            actionCard.isVisible = false
            verseSelectables = false
            btnDelete.isVisible = false
            readerBtnProcess.isVisible = true
            binding.readerRefsText.text =""
            pageViewModel.setIndex(chapterKey, verseSelectables)

        }

        readerBtnProcess.apply {
            setOnClickListener {
                btnDelete.isVisible = true
                isVisible = false
                actionCard.isVisible = true


            }
        }

        pageViewModel.chapters.observe(viewLifecycleOwner, Observer {
            var cvi_adapter = ChapterVerseitemRecyclerViewAdapter(it)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = cvi_adapter
            }

            cvi_adapter.onCheckChanged ={
                onCheckChanged(it)
            }
            cvi_adapter.onItemLongClick ={
                if (!verseSelectables){
                    verseSelectables = true
                    pageViewModel.makeLoadables(chapterKey,verseSelectables,it.position)
                    if (it.position>6){
                        recyclerView.scrollToPosition(it.position-4)
                    }
                    SelectorCard.isVisible = true
                    onLongItemClick( it)
                }

            }
        })

        pageViewModel.referenceText.observe(viewLifecycleOwner, Observer {
            binding.readerRefsText.text =  it
        })

        return root
    }

    companion object {
        //These help create an instance of a chapter
        private const val ARG_SECTION_NUMBER = "chapter"
        private const val ARG_BOOK_NAME="book_name"
        private const val ARG_CHAPTER_KEY = "chapter_key"

        //annotation allows static(jav type) access to this method
        @JvmStatic
        fun newInstance(sectionNumber: Int, chapterKey:String,bookName:String): Chapters {
            return Chapters().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString(ARG_CHAPTER_KEY, chapterKey)
                    putString(ARG_BOOK_NAME,bookName)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCheckChanged(cv: ClickedVerse) {
        if (cv.check) versesPicked.add(cv.position+1) else versesPicked.remove(cv.position+1)

        pageViewModel.makeReference(bookName, sectionNumber, versesPicked)
    }

    private fun onLongItemClick(cv: ClickedVerse){
        versesPicked.clear()
    }

}