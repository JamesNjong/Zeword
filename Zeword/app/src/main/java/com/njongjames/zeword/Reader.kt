package com.njongjames.zeword

import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.njongjames.zeword.ui.ReaderSectionsPagerAdapter
import com.njongjames.zeword.databinding.ActivityReaderBinding
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Reader : AppCompatActivity() {

    private lateinit var binding: ActivityReaderBinding

    @Inject
    lateinit var pvm: PrimarilyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pos : Int = intent.getIntExtra("Position",0)
        var bookx = when(intent.getIntExtra("Tab",0)){
            0->pvm.utilz.gdo.bookList.get(pos)
            1->pvm.utilz.gdo.oldbookList.get(pos)
            else -> pvm.utilz.gdo.newbookList.get(pos)
        }

        val sectionsPagerAdapter = ReaderSectionsPagerAdapter(this, supportFragmentManager,bookx)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        binding.title.text = bookx.book

        var chapt: TextView = binding.chapterCount
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                chapt.text = "Chapter ${position+1}"
            }
            override fun onPageSelected(position: Int) {

            }

        })
    }
}