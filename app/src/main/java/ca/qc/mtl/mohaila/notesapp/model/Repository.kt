package ca.qc.mtl.mohaila.notesapp.model

import android.arch.lifecycle.LiveData

interface Repository {
    fun insert(note: Note)
    fun update(note: Note)
    fun delete(note: Note)
    fun deleteAllNotes()
    fun getAllNotes(): LiveData<List<Note>>
}

