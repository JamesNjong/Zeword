package xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.sheets

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import xyz.thisisjames.boulevard.android.zeword.R
import xyz.thisisjames.boulevard.android.zeword.databinding.FragmentActionSheetBinding
import xyz.thisisjames.boulevard.android.zeword.frontend.viewmodels.ChaptersViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ActionSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var primVM: PrimarilyViewModel


    private val viewModel : ChaptersViewModel by viewModels()

    private var _binding : FragmentActionSheetBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentActionSheetBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.close.setOnClickListener {
            resetView()
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.setIndex(viewModel.sectionNumber, false)
        primVM.switchFabFunction(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun resetView(){
        dismiss()
        viewModel.setIndex(viewModel.sectionNumber, false)
    }

}