package com.njongjames.zeword.rooms

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.njongjames.zeword.rooms.tables.ZenoteTable

@Dao
interface ZeDao {
    // below is a generic insert method for
    // adding a new entry to our database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insertNote(thing : ZenoteTable)

    // below is a generic delete method
    // for deleting anything.
    @Delete
    suspend fun deleteNote(thing : ZenoteTable)

    // below is a generic method is use to update the anything.
    @Update
    suspend fun updateNote(thing: ZenoteTable)


    // below is the method to read all the notes
    // from our database we have specified the query for it.
    // inside the query we are arranging it in ascending
    // order on below line and we are specifying
    // the table name from which
    // we have to get the data.
    @Query("Select * from zenotesTable order by timestamp ASC")
    fun getAllNotes(): LiveData<List<ZenoteTable>>

}