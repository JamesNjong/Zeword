package com.njongjames.zeword.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.njongjames.zeword.Models.LoadableVerse
import com.njongjames.zeword.Models.versStatus
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var pvm: PrimarilyViewModel

    private val _chapters = MutableLiveData<List<String>>()

    private val loadables = MutableLiveData<LoadableVerse>()

    val  chapters: LiveData<LoadableVerse> = loadables

    var selects= false


    fun setIndex(chapterKey: String, select:Boolean) {

        selects = select
        var chapterIndex:Int = pvm.utilz.gdo.chapterKeys.indexOf(chapterKey)
        var verses = pvm.utilz.gdo.bibleChapters.get(chapterIndex).verse?.split("##")!!
        var checks = verseChecks(verses.size)

        var  ldv = LoadableVerse( verses, checks, selects )

        loadables.value = ldv
    }

    fun makeLoadables(chapterKey: String, select:Boolean, position:Int){
        selects = select
        var chapterIndex:Int = pvm.utilz.gdo.chapterKeys.indexOf(chapterKey)
        var verses = pvm.utilz.gdo.bibleChapters.get(chapterIndex).verse?.split("##")!!
        var checks = verseChecks(verses.size)

        checks.add(position, versStatus(position,true))
        checks.removeAt(position+1)

        var  ldv = LoadableVerse( verses, checks, selects )


        loadables.value = ldv
    }

    private fun verseChecks(count:Int):ArrayList<versStatus>{
        var checks:ArrayList<versStatus> = ArrayList()

        while (checks.size<count){
            checks.add(versStatus(checks.size,false))
        }
        return checks
    }



    private  var _referenceText = MutableLiveData<String>()
    var referenceText :LiveData<String> =_referenceText


    private var refs:ArrayList<String> = ArrayList()
    fun makeReference(book:String,chapter:Int,pickedVerses:ArrayList<Int>){
        var _pickedVerses =pickedVerses.sorted()
        refs.clear()
        var k:Int =1
        if (_pickedVerses.size>1){

            var start = _pickedVerses.get(0)
            var stop = _pickedVerses.get(0)
            while (k<pickedVerses.size){
                if (_pickedVerses.get(k) != _pickedVerses.get(k-1)+1 ){
                    adRefString(start,stop)
                    start = _pickedVerses.get(k)
                    stop = start
                }else{
                    stop = _pickedVerses.get(k)
                }
                k=k+1 /** increment the count */
            }
            adRefString(start,stop)

            if (refs.size>1){
                var reference = ""
                val reffs = refs.toSet()
                reffs.forEach { reference+="${it}," }
                reference = reference.substring(0,reference.length-1) // remove the last comma
                _referenceText.value = "${book} ${chapter}: ${reference}"
            }else{
                _referenceText.value = "${book} ${chapter}: ${refs.get(0)}"
            }


        }else{
            _referenceText.value = "${book} ${chapter}: ${_pickedVerses.get(0)}"
        }

        referenceText = _referenceText
    }

    fun adRefString(start:Int,stop:Int){

        if (start!=stop){
            refs.add("${start}-${stop}")
        }else{
            refs.add("${start}")
        }
    }


}