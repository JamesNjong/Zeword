package com.njongjames.zeword.rooms.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// on line below we are specifying our table name
@Entity(tableName = "zenotesTable")


// on class constructor, we are specifying our column info
// and inside that we are passing our column name
class ZenoteTable (@ColumnInfo(name = "Book")val zenoteBook :String,
                   @ColumnInfo(name = "Chapter")val zenoteChapter :Int,
                   @ColumnInfo(name = "Reference")val zenoteReference :String,
                   @ColumnInfo(name = "Snippet")val zenoteSnippet :String,
                   @ColumnInfo(name = "Content")val zenoteContent :String,
                   @ColumnInfo(name = "timestamp")val zetimeStamp :Long) {
    // on below line we are specifying our key and
    // then auto generate as true and we are
    // specifying its initial value as 0
    @PrimaryKey(autoGenerate = true) var id = 0
}