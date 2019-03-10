package ca.qc.mtl.mohaila.notesapp.model

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomRepository(application: Application) : Repository {
    private val db: AppDatabase = AppDatabase.getInstance(application)
    private val noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>

    init {
        noteDao = db.noteDao()
        allNotes = noteDao.getAll()
    }

    override fun insert(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }
    }

    override fun update(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.update(note)
        }
    }

    override fun delete(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.delete(note)
        }
    }

    override fun deleteAllNotes() {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteAll()
        }
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}