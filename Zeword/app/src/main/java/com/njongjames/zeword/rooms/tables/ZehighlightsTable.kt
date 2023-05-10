package com.njongjames.zeword.rooms.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// on line below we are specifying our table name
@Entity(tableName = "zehighlightsTable")



class ZehighlightsTable (@ColumnInfo(name = "Book")val zenoteBook :String,
                         @ColumnInfo(name = "Chapter")val zenoteChapter :Int,
                         @ColumnInfo(name = "Reference")val zenoteReference :String,
                         @ColumnInfo(name = "Snippet")val zenoteSnippet :String,
                         @ColumnInfo(name = "Type")val zenoteContent :String,
                         @ColumnInfo(name = "timestamp")val zetimeStamp :Long) {
    // on below line we are specifying our key and
    // then auto generate as true and we are
    // specifying its initial value as 0
    @PrimaryKey(autoGenerate = true)
    var id = 0

}