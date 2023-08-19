package com.dkgtech.notesappdarkmode.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dkgtech.notesappdarkmode.dao.NoteDao
import com.dkgtech.notesappdarkmode.model.NoteModel

@Database(entities = [NoteModel::class], exportSchema = false, version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun myNotesDao(): NoteDao

    companion object {
        @Volatile
        var INSTANCE: NotesDatabase? = null

        fun getDatabaseInstance(context: Context): NotesDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "notes_db")
                        .allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }

}