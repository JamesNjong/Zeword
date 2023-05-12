package com.njongjames.zeword.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.njongjames.zeword.R
import com.njongjames.zeword.databinding.FragmentHomeBinding
import com.njongjames.zeword.ui.MainSectionsPagerAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    lateinit var allBooks: TextView
    lateinit var oldBooks: TextView
    lateinit var newBooks: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup view pager
        val viewPager: ViewPager = binding.booksPager
        val sectionsPager = MainSectionsPagerAdapter(requireContext(),childFragmentManager)
        viewPager.adapter = sectionsPager

        allBooks = binding.allBooks
        allBooks.setOnClickListener{
            viewPager.setCurrentItem(0)
        }
        oldBooks = binding.oldBooks
        oldBooks.setOnClickListener{viewPager.setCurrentItem(1)}
        newBooks = binding.newBooks
        newBooks.setOnClickListener{viewPager.setCurrentItem(2)}

        viewPager.addOnPageChangeListener(  object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                when(position){
                    0->{
                        allBooks.background =  context?.getDrawable(R.drawable.selected_tab_indicator)
                        oldBooks.background = context?.getDrawable(R.color.off_white1)
                        newBooks.background = context?.getDrawable(R.color.off_white1)
                        context?.getColor(R.color.off_white1)?.let { allBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { oldBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { newBooks.setTextColor(it) }

                        oldBooks.text = "OT"
                        newBooks.text = "NT"
                    }
                    1->{
                        oldBooks.background =  context?.getDrawable(R.drawable.selected_tab_indicator)
                        allBooks.background = context?.getDrawable(R.color.off_white1)
                        newBooks.background = context?.getDrawable(R.color.off_white1)
                        context?.getColor(R.color.off_white1)?.let { oldBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { allBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { newBooks.setTextColor(it) }

                        oldBooks.text = "Old Testament"
                        newBooks.text = "NT"
                    }
                    else->{
                        newBooks.background =  context?.getDrawable(R.drawable.selected_tab_indicator)
                        oldBooks.background = context?.getDrawable(R.color.off_white1)
                        allBooks.background = context?.getDrawable(R.color.off_white1)
                        context?.getColor(R.color.off_white1)?.let { newBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { oldBooks.setTextColor(it) }
                        context?.getColor(R.color.text_secondary)?.let { allBooks.setTextColor(it) }
                        oldBooks.text = "OT"
                        newBooks.text = "New Testament"
                    }
                }
            }
            override fun onPageSelected(position: Int) {
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}