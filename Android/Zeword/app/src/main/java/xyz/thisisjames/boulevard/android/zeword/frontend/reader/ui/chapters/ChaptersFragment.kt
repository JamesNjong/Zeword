package xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.chapters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.Backend.data.ClickedVerse
import xyz.thisisjames.boulevard.android.zeword.R
import xyz.thisisjames.boulevard.android.zeword.databinding.FragmentChaptersBinding
import xyz.thisisjames.boulevard.android.zeword.frontend.viewmodels.ChaptersViewModel
import xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters.VerseitemAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ChaptersFragment : Fragment() {

    private var _binding: FragmentChaptersBinding? = null

    @Inject
    lateinit var pvm: PrimarilyViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ChaptersViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val  page = arguments?.getInt(ARG_SECTION_NUMBER)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentChaptersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ChaptersViewModel::class.java).apply {
            arguments?.getInt(ARG_SECTION_NUMBER)?.let { setIndex(it, false) }
        }

        viewModel.chapters.observe(viewLifecycleOwner, Observer {

            var cvi_adapter = VerseitemAdapter(it,onItemClicked,onItemLongClicked)

            binding.chapterList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = cvi_adapter
            }

        })

    }


    private val onItemClicked : (ClickedVerse)->Unit = {
        if (it.check) viewModel.versesPicked.add(it.position+1) else viewModel.versesPicked.remove(it.position+1)
        viewModel.makeReference()
    }



    private val onItemLongClicked : (ClickedVerse) -> Unit = {
        if (!viewModel.verseSelectables){
            viewModel.verseSelectables = true
            viewModel.makeLoadables(true,it.position)
            if (it.position>6){
                binding.chapterList.scrollToPosition(it.position-4)
            }
            pvm.switchFabFunction(1)
            viewModel.versesPicked.clear()
        }


    }




    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): ChaptersFragment {
            return ChaptersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }



}