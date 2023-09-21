package com.njongjames.zeword.di

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Bookz
import xyz.thisisjames.boulevard.android.zeword.Backend.data.GlobalDataObject
import xyz.thisisjames.boulevard.android.zeword.Backend.dependencyinjections.JSONHelpers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrimarilyViewModel @Inject constructor(@ApplicationContext _context:Context) : ViewModel()   {

    private  val context: Context

    //this is the object holding all unpacked JSON data and access keys
    val  gdo : GlobalDataObject

    private  var _currentBook = MutableLiveData<Bookz>()
    val currentBook : LiveData<Bookz> = _currentBook

    private var _currentPosition = MutableLiveData<Int>()
    var currentPosition: LiveData<Int>  = _currentPosition.map { it }

    var currentGroup: String ?= null
    var currentGroupSize : Int = 0


    private var _fabFunctionSwitch = MutableLiveData<Int>()
    var fabFunctionSwitch : LiveData<Int> = _fabFunctionSwitch


    var focusGroup : List<Bookz> = ArrayList()

    init {
        context = _context
        gdo = loadGDO()
    }

    fun switchFabFunction(int:Int){
        _fabFunctionSwitch.value = int
    }

    fun setBook(bookz: Bookz){
        _currentBook.value = bookz
    }

    fun setPosition(pos : Int){
        _currentPosition.value = pos
    }


    // this function is used to load the books of interest on item clicked in the home page
    fun prepareFocus (groupType:String, groupName :String ) {

        focusGroup = if (groupType == "Groups") getTestamentBooks(groupName) else
            getGroupBooks(groupName)

        setBook(focusGroup.get(0))
        setPosition(0)

        currentGroup = groupName
        currentGroupSize = focusGroup.size

    }



    fun getTestamentBooks(testment:String) : List<Bookz> {
        val compare = if(testment == "Old testament") "Old" else "New"
        return gdo.bookList.filter { compare == it.testament }
    }

    fun getGroupBooks(groupName:String) : List<Bookz> {
        return gdo.bookList.filter { it.group == groupName }
    }


    //the following function is responsible for populating the GDO(GlobalDataObject)
    fun loadGDO(): GlobalDataObject {
        var gdobj: GlobalDataObject = GlobalDataObject()

        val jsonHelper : JSONHelpers = JSONHelpers()

        with(gdobj){
            bibleChapters = jsonHelper.getJSONVerses(context)
            chapterKeys = jsonHelper.makeAccessKeys(bibleChapters)
            bookList = jsonHelper.getJSONBooks(context)
        }

        return gdobj
    }






}