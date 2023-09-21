package xyz.thisisjames.boulevard.android.zeword.Backend.dependencyinjections

import android.content.Context
import android.util.Log
import org.json.JSONObject
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Bookz
import xyz.thisisjames.boulevard.android.zeword.Backend.data.Chapter
import java.io.IOException
import java.nio.charset.Charset

class JSONHelpers  {

    /**
     * Method to load the JSON from the Assets file and return the object
     *
     * function takes filename with extension as a string
     */
    fun getJSONFromAssets(filenamewithdotjson:String, context : Context): String? {

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


    /**
     *  extract books from json file
     */

    fun getJSONBooks(context : Context):ArrayList<Bookz> {

        val bookList: ArrayList<Bookz> = ArrayList()

        val objs = JSONObject(getJSONFromAssets("BibleBooks.json", context))

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
                    group= bk.getString("Group")
                }
                bookList.add(bks)
            }

        }catch (ex:Exception){
            Log.d("Eexcept", ex.message.toString())
        }

        return bookList
    }


    fun getJSONVerses(context : Context):ArrayList<Chapter> {

        val bibleChapter: ArrayList<Chapter> = ArrayList()

        val objs = JSONObject(getJSONFromAssets("BibleChapters.json", context))

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


    //this method makes the access keys for verses
    fun makeAccessKeys(chapters:ArrayList<Chapter>):ArrayList<String>{
        val chapterKeys:ArrayList<String> = ArrayList()

        chapters.forEach { it-> chapterKeys.add("${it.book}-${it.chapter}") }

        return chapterKeys
    }



}