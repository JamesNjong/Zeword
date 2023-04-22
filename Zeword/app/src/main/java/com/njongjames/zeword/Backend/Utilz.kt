package com.njongjames.zeword.Backend

import android.content.Context
import android.util.Log
import com.njongjames.zeword.Models.Bookz
import com.njongjames.zeword.Models.Chapter
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Utilz @Inject constructor (@ApplicationContext _context:Context) {


    lateinit var context: Context

    var gdo : GlobalDataObject

    init {
        context = _context
        gdo = loadGDO()
    }

     fun loadGDO(): GlobalDataObject {
         var gdobj: GlobalDataObject = GlobalDataObject()

        with(gdobj){
            bibleChapters = getJSONVerses()
            chapterKeys = makeAccessKeys(bibleChapters)
            bookList = getJSONBooks()
            oldbookList= getJSONBooks("Old")
            newbookList = getJSONBooks("New")
        }

         return gdobj
     }


    /**
     * Method to load the JSON from the Assets file and return the object
     *
     * function takes filename with extension as a string
     */
    fun getJSONFromAssets(filenamewithdotjson:String): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = context.assets.open(filenamewithdotjson)
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


     fun getJSONBooks(testamen:String = "none"):ArrayList<Bookz> {

        val bookList: ArrayList<Bookz> = ArrayList()

        val objs = JSONObject(getJSONFromAssets("BibleBooks.json"))

        val obj = objs.getJSONArray("bibleBooks")

        try {

            for (i in 0 until obj.length()){
                var bk = obj.getJSONObject(i)
                var bks: Bookz = Bookz()
                with(bks){
                    book = bk.getString("Book")
                    chaptercount = bk.getInt("ChapterCount")
                    abbrevation = bk.getString("Abrev")
                    testament = bk.getString("Testament")
                }


                when(testamen){
                    "none"-> bookList.add(bks)
                    else -> if (testamen == bks.testament)  bookList.add(bks)
                }
            }

        }catch (ex:Exception){
            Log.d("Eexcept", ex.message.toString())
        }

        return bookList
    }

     fun getJSONVerses():ArrayList<Chapter> {

        val bibleChapter: ArrayList<Chapter> = ArrayList()

        val objs = JSONObject(getJSONFromAssets("BibleChapters.json"))

        val obj = objs.getJSONObject("bible")

        try {
            val stringIterator: Iterator<String> = obj.keys()

            stringIterator.forEach { it->
                run {
                    val chapters = obj.getJSONObject(it)
                    val thisChapter = Chapter()
                    thisChapter.apply {
                        book =  chapters.getString("Book")
                        chapter = chapters.getString("Chapter")
                        verse =  chapters.getString("Verses")
                    }
                    bibleChapter.add(thisChapter)
                 }
            }
        }catch (ex:Exception){
            Log.d("Eexcept", ex.message.toString())
        }

        return bibleChapter
    }

    fun makeAccessKeys(chapters:ArrayList<Chapter>):ArrayList<String>{
        val chapterKeys:ArrayList<String> = ArrayList()

        chapters.forEach { it-> chapterKeys.add("${it.book}-${it.chapter}") }

        return chapterKeys
    }

}