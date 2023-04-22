package com.njongjames.zeword.Models

data class SearchResultItem (
    var book:String,
    var chapter:String,
    var verse:Int,
    var text:String,
    var resultType:Int = 0,
    var testamen:String
)

/**
 * result type is zero for verse text and 1 for books. . . ie if the value searched is a book, set result type to 1*/