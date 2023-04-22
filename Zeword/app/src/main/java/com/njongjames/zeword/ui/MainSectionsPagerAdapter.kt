package com.njongjames.zeword.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainSectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return Books.newInstance(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            1->"Old Testament"
            2->"New Testament"
            else->"All"
        }
    }

}