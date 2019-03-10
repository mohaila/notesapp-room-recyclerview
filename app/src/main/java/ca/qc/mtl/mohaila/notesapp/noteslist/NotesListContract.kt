package ca.qc.mtl.mohaila.notesapp.noteslist

import ca.qc.mtl.mohaila.notesapp.model.Note

interface NotesListContract {
    interface View {
        fun updateNotesList(notes: List<Note>)
    }

    interface Presenter {
        fun startAddNoteActivity ()
        fun startEditNoteActivity (note: Note)
        fun updateNote(note: Note)
        fun deleteAllNotes()
        fun deleteNote(note: Note) {
        }
    }
}