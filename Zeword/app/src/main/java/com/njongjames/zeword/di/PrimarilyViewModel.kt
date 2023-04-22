package com.njongjames.zeword.di

import androidx.lifecycle.ViewModel
import com.njongjames.zeword.Backend.Utilz
import com.njongjames.zeword.Models.Bookz
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrimarilyViewModel @Inject constructor(val utilz: Utilz) : ViewModel()   {


    fun getBooks(tab:Int) : ArrayList<Bookz> {
        return when(tab){
            0->utilz.gdo.bookList
            1->utilz.gdo.oldbookList
            else->utilz.gdo.newbookList
        }
    }

    var currentBook: String ?=null

    var currentPosition: Int ?=null
}