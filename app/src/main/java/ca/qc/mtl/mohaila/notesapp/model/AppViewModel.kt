package ca.qc.mtl.mohaila.notesapp.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val appRepository : Repository
    private val allNotes: LiveData<List<Note>>

    init {
        appRepository = RoomRepository(application)
        allNotes = appRepository.getAllNotes()
    }

    fun insert(note: Note) {
        appRepository.insert(note)
    }

    fun update(note: Note) {
        appRepository.update(note)
    }

    fun delete(note: Note) {
        appRepository.delete(note)
    }

    fun deleteAllNotes() {
        appRepository.deleteAllNotes()
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return allNotes
    }
}