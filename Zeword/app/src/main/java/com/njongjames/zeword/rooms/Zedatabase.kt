package com.njongjames.zeword.rooms

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.njongjames.zeword.rooms.tables.ZehighlightsTable
import com.njongjames.zeword.rooms.tables.ZenoteTable


@Database(entities = arrayOf(ZenoteTable::class, ZehighlightsTable::class), version = 1, exportSchema = false)
abstract class Zedatabase : RoomDatabase(){

    abstract fun getZeDao(): ZeDao


    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: Zedatabase? = null

        fun getDatabase(context: Context): Zedatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Zedatabase::class.java,
                    "ze_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}