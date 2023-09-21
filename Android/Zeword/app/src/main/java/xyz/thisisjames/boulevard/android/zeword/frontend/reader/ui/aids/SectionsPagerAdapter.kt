package xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.aids

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.thisisjames.boulevard.android.zeword.frontend.reader.ui.chapters.ChaptersFragment

class SectionsPagerAdapter (private val count : Int, fm : FragmentManager)
    : FragmentPagerAdapter(fm){


    override fun getCount(): Int {
        return count
    }

    override fun getItem(position: Int): Fragment {
        return ChaptersFragment.newInstance(position)
    }


}