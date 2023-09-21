package xyz.thisisjames.boulevard.android.zeword.frontend.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Groups
import xyz.thisisjames.boulevard.android.zeword.databinding.FragmentHomeBinding
import xyz.thisisjames.boulevard.android.zeword.listmanagers.adapters.GroupsAdapter
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ReaderActivity
import xyz.thisisjames.boulevard.android.zeword.frontend.viewmodels.HomeViewModel
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModels()

    @Inject
    lateinit var primVM: PrimarilyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookList.apply {
            adapter = GroupsAdapter(viewModel.generateList(context),itemClicked)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val itemClicked : (groups : Groups)->Unit = {

        primVM.prepareFocus(it.groupType, it.name)

        val intent = Intent(context, ReaderActivity::class.java)
        intent.putExtra("Group", it.name)
        intent.putExtra("Count", it.count)
        intent.putExtra("GroupType", it.groupType)
        startActivity(intent)
    }
}