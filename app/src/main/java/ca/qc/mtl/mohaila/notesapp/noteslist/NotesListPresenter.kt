package ca.qc.mtl.mohaila.notesapp.noteslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import ca.qc.mtl.mohaila.notesapp.addeditnote.AddEditNoteActivity
import ca.qc.mtl.mohaila.notesapp.model.AppViewModel
import ca.qc.mtl.mohaila.notesapp.model.Note

class NotesListPresenter(private val view: NotesListContract.View) : NotesListContract.Presenter {

    private val activity = view as NotesListActivity
    private val appViewModel: AppViewModel

    init {
        appViewModel = ViewModelProviders.of(activity).get(AppViewModel::class.java)
        appViewModel.getAllNotes().observe(activity, Observer {
            view.updateNotesList(it!!)
        })
    }

    override fun startAddNoteActivity() {
        val intent = Intent(activity, AddEditNoteActivity::class.java)
        intent.putExtra(EXTRA_ACTION, ACTION_ADD)
        activity.startActivity(intent)
    }

    override fun startEditNoteActivity(note: Note) {
        val intent = Intent(activity, AddEditNoteActivity::class.java)
        intent.putExtra(EXTRA_NOTE, note)
        intent.putExtra(EXTRA_ACTION, ACTION_EDIT)
        activity.startActivity(intent)
    }

    override fun updateNote(note: Note) {
        appViewModel.update(note)
    }

    override fun deleteAllNotes() {
        appViewModel.deleteAllNotes()
    }

    override fun deleteNote(note: Note) {
        appViewModel.delete(note)
    }

    companion object {
        const val EXTRA_NOTE = "model.note"
        const val EXTRA_ACTION = "action.note"
        const val ACTION_ADD = 1
        const val ACTION_EDIT = 2
    }
}