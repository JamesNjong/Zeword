package com.njongjames.zeword.Models

data class LoadableVerse(
    var  verses:List<String>,
    var checkStates:List<versStatus>,
    var viewStyle:Boolean
)


data class versStatus(
    var position:Int=1,
    var status:Boolean = false
)