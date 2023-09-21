package xyz.thisisjames.boulevard.android.zeword.Backend.data


data class GlobalDataObject constructor(
    var chapterKeys:ArrayList<String> = ArrayList(),
    var bookList: ArrayList<Bookz> = ArrayList(),
    var bibleChapters: ArrayList<Chapter> = ArrayList()
)


data class Groups(
    var groupType : String,
    var name : String,
    var count : Int,
    var header : Boolean = false
)


data class LoadableVerse(
    var  verses:List<String>,
    var checkStates:List<versStatus>,
    var viewStyle:Boolean
)


data class versStatus(
    var position:Int=1,
    var status:Boolean = false
)

data class Bookz(
    var book: String? = null,
    var chaptercount: Int? = null,
    var testament: String? = null,
    var abbrevation:String? = null,
    var group :String? = null
)

data class Chapter(
    var book: String? = null,
    var chapter: String? = null,
    var verse: String? = null
)


data class ClickedVerse(
    var position: Int,
    var check: Boolean,
    var text:String="Empty"
)

data class ReferenceObjects(
    var start:Int=0,
    var stop:Int=0
)


data class SearchResultItem (
    var book:String,
    var chapter:String,
    var verse:Int,
    var text:String,
    var resultType:Int = 0,
    var testamen:String
)


data class StorableObject(
    var reference:String,
    var content:String,
    var type:String,
    var time:Long,
    var member:String
)

