package com.dkgtech.notesappdarkmode.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dkgtech.notesappdarkmode.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<NoteModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteModel: NoteModel)

    @Query("DELETE FROM notes WHERE id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(noteModel: NoteModel)

//    custom function

    @Query("SELECT * FROM NOTES WHERE priority=3")
    fun getHighNotes() : LiveData<List<NoteModel>>

    @Query("SELECT * FROM NOTES WHERE priority=2")
    fun getMediumNotes() : LiveData<List<NoteModel>>

    @Query("SELECT * FROM NOTES WHERE priority=1")
    fun getLowNotes() : LiveData<List<NoteModel>>
}