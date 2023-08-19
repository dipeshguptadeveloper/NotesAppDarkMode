package com.dkgtech.notesappdarkmode.repository

import androidx.lifecycle.LiveData
import com.dkgtech.notesappdarkmode.dao.NoteDao
import com.dkgtech.notesappdarkmode.model.NoteModel

class NotesRepository(val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return noteDao.getAllNotes()
    }

    fun addNote(noteModel: NoteModel) {
        noteDao.addNote(noteModel)
    }

    fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    fun updateNote(noteModel: NoteModel) {
        noteDao.updateNote(noteModel)
    }


//    custom functions

    fun getHighNotes(): LiveData<List<NoteModel>> {
        return noteDao.getHighNotes()
    }

    fun getMediumNotes(): LiveData<List<NoteModel>> {
        return noteDao.getMediumNotes()
    }

    fun getLowNotes(): LiveData<List<NoteModel>> {
        return noteDao.getLowNotes()
    }

}