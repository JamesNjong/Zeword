package com.njongjames.zeword.Backend

import com.njongjames.zeword.Models.Bookz
import com.njongjames.zeword.Models.Chapter


data class GlobalDataObject constructor(
    var chapterKeys:ArrayList<String> = ArrayList(),
    var bookList: ArrayList<Bookz> = ArrayList(),
    var oldbookList: ArrayList<Bookz> = ArrayList(),
    var newbookList: ArrayList<Bookz> = ArrayList(),
    var bibleChapters: ArrayList<Chapter> = ArrayList()
)