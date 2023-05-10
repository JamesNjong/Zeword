package com.njongjames.zeword.rooms

import androidx.lifecycle.LiveData
import androidx.room.Entity
import com.njongjames.zeword.rooms.tables.ZenoteTable


/*
We again are using generic functions here to allow for more re-usability of our code
 */

class ZeRepository (private val zedao : ZeDao){


    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<ZenoteTable>> = zedao.getAllNotes()

    // on below line we are creating an insert method
    // for adding the note to our database.
    suspend fun<T> insert(thing: T) {
        zedao.insert(thing)
    }

    // on below line we are creating a delete method
    // for deleting our content in the database.
    suspend fun<T> delete(thing: T){
        zedao.delete(thing)
    }

    // on below line we are creating a update method for
    // updating our content in the database.
    suspend fun<T> update(thing: T){
        zedao.update(thing)
    }


}