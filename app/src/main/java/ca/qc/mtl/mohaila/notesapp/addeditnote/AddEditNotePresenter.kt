package ca.qc.mtl.mohaila.notesapp.addeditnote

import android.arch.lifecycle.ViewModelProviders
import ca.qc.mtl.mohaila.notesapp.R
import ca.qc.mtl.mohaila.notesapp.model.AppViewModel
import ca.qc.mtl.mohaila.notesapp.model.Note

class AddEditNotePresenter(private val view: AddEditNoteContract.View) : AddEditNoteContract.Presenter {
    private val activity = view as AddEditNoteActivity
    private val appViewModel: AppViewModel

    init {
        appViewModel = ViewModelProviders.of(activity).get(AppViewModel::class.java)
    }

    override fun saveNote() {
        val name = view.getNoteName()
        if (name.isEmpty()) {
            view.displayMessage(R.string.note_name_empty_error)
            return
        }

        val description = view.getNoteDescription()
        if(description.isEmpty()) {
            view.displayMessage(R.string.note_desc_empty_error)
            return
        }

        val urgent = view.getNoteUrgent()

        val note = Note(name = name, description = description, urgent = urgent)
        appViewModel.insert(note)

        view.displayMessage(R.string.note_saved)
        view.clear()
    }

    override fun updateNote(note: Note) {
        val name = view.getNoteName()
        if (name.isEmpty()) {
            view.displayMessage(R.string.note_name_empty_error)
            return
        }

        val description = view.getNoteDescription()
        if(description.isEmpty()) {
            view.displayMessage(R.string.note_desc_empty_error)
            return
        }

        val urgent = view.getNoteUrgent()

        val newNote = Note(note.id, name, description, urgent)
        appViewModel.update(newNote)

        view.displayMessage(R.string.note_updated)
    }
}