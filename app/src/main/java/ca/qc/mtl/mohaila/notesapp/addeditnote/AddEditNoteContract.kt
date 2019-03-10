package ca.qc.mtl.mohaila.notesapp.addeditnote

import ca.qc.mtl.mohaila.notesapp.model.Note

interface AddEditNoteContract {
    interface View {
        fun getNoteName(): String
        fun getNoteDescription(): String
        fun getNoteUrgent(): Boolean
        fun displayMessage(id: Int)
        fun clear()
    }

    interface Presenter {
        fun saveNote()
        fun updateNote(note: Note) {

        }
    }
}