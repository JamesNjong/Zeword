package xyz.thisisjames.boulevard.android.zeword.frontend.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.njongjames.zeword.di.PrimarilyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.thisisjames.boulevard.android.zeword.Backend.data.LoadableVerse
import xyz.thisisjames.boulevard.android.zeword.Backend.data.versStatus
import javax.inject.Inject
import javax.inject.Singleton


@HiltViewModel
class ChaptersViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var pvm: PrimarilyViewModel

    private val loadables = MutableLiveData<LoadableVerse>()

    val  chapters: LiveData<LoadableVerse> = loadables

    var selects= false // variable determines if checkboxes are displayed

    //captures references for selected verses
    private  var _referenceText = MutableLiveData<String>()
    var referenceText : LiveData<String> =_referenceText

    private var refs:ArrayList<String> = ArrayList()

    var snippet : String = ""

    lateinit var chapterKey: String
    lateinit var bookName:String
    var sectionNumber: Int =0


    var versesPicked=ArrayList<Int>()

    var verseSelectables = false


    fun setIndex(sectionNumber:Int, select : Boolean){

        selects = select

        chapterKey = "${pvm.currentBook.value!!.book}-Chapter ${sectionNumber+1}"
        this.bookName = pvm.currentBook.value!!.book!!
        this.sectionNumber = sectionNumber

        var chapterIndex:Int = pvm.gdo.chapterKeys.indexOf(chapterKey)
        var verses = pvm.gdo.bibleChapters.get(chapterIndex).verse?.split("##")!!
        var checks = verseChecks(verses.size)

        var  ldv = LoadableVerse( verses, checks, selects )

        loadables.value = ldv
    }



    fun makeLoadables( select:Boolean, position:Int){
        selects = select
        var chapterIndex:Int = pvm.gdo.chapterKeys.indexOf(chapterKey)
        var verses = pvm.gdo.bibleChapters.get(chapterIndex).verse?.split("##")!!
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


    fun makeReference( ){
        var _pickedVerses =versesPicked.sorted()
        refs.clear()
        var k:Int =1
        if (_pickedVerses.size>1){

            var start = _pickedVerses.get(0)
            var stop = _pickedVerses.get(0)
            while (k<versesPicked.size){
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
                _referenceText.value = "${pvm.currentBook.value!!.book!!} ${sectionNumber+1}: ${reference}"
            }else{
                _referenceText.value = "${pvm.currentBook.value!!.book!!} ${sectionNumber+1}: ${refs.get(0)}"
            }


        }else{
            _referenceText.value = "${pvm.currentBook.value!!.book!!} ${sectionNumber+1}: ${_pickedVerses.get(0)}"
        }

        referenceText = _referenceText //update the live data upject with its mutable version

        makeSnippet()
    }


    //here we are creating the reference string
    fun adRefString(start:Int,stop:Int){

        if (start!=stop){
            refs.add("${start}-${stop}")
        }else{
            refs.add("${start}")
        }

    }




    fun makeSnippet(){
        var verses = pvm.gdo.bibleChapters.get(sectionNumber).verse?.split("##")!!
        var pv = versesPicked
        pv.sort()

        for (i in pv){
            snippet  += "[${i}] ${verses.get(i)}\n"
        }
    }








}