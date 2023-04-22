package com.njongjames.zeword.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.njongjames.zeword.Models.Bookz


class ReaderSectionsPagerAdapter(private val context: Context, fm: FragmentManager, _book:Bookz) :
    FragmentPagerAdapter(fm) {

    var bookz:Bookz
    var currentChapt = 1
    init {
        bookz = _book
    }

    private val _text = MutableLiveData<String>()
    val chapterDisplay: LiveData<String> = _text

    override fun getItem(position: Int): Fragment {
        _text.apply {
            value = "${bookz.book} ${position}"
        }

        var chapterKey = "${bookz.book}-Chapter ${position+1}"

        return Chapters.newInstance(position+1,chapterKey,bookz.book!!)
    }

    override fun getCount(): Int {
        return bookz.chaptercount!!
    }


}