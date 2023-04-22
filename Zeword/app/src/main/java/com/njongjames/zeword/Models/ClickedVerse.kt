package com.njongjames.zeword.Models

import android.view.View

data class ClickedVerse(
    var position: Int,
    var check: Boolean,
    var text:String="Empty"
)
