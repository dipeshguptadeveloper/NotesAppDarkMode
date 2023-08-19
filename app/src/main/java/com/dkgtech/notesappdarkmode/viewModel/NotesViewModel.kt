package com.dkgtech.notesappdarkmode.viewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dkgtech.notesappdarkmode.database.NotesDatabase
import com.dkgtech.notesappdarkmode.model.NoteModel
import com.dkgtech.notesappdarkmode.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    val notesRepository: NotesRepository


    init {
        val noteDao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        notesRepository = NotesRepository(noteDao)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return notesRepository.getAllNotes()
    }

    fun addNote(noteModel: NoteModel) {
        notesRepository.addNote(noteModel)
    }

    fun deleteNote(id: Int) {
        notesRepository.deleteNote(id)
    }

    fun updateNote(noteModel: NoteModel) {
        notesRepository.updateNote(noteModel)
    }



//    custom functions

    fun getHighNotes(): LiveData<List<NoteModel>> {
        return notesRepository.getHighNotes()
    }

    fun getMediumNotes(): LiveData<List<NoteModel>> {
        return notesRepository.getMediumNotes()
    }

    fun getLowNotes(): LiveData<List<NoteModel>> {
        return notesRepository.getLowNotes()
    }

}