package ca.qc.mtl.mohaila.notesapp.noteslist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import ca.qc.mtl.mohaila.notesapp.R
import ca.qc.mtl.mohaila.notesapp.model.Note
import kotlinx.android.synthetic.main.activity_notes_list.*

class NotesListActivity : AppCompatActivity(), NotesListContract.View {

    private lateinit var presenter: NotesListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        presenter = NotesListPresenter(this)

        recyclerview.adapter = NotesListAdapter(presenter)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)


        fab.setOnClickListener {
            presenter.startAddNoteActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.notes_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.notes_delete_all -> presenter.deleteAllNotes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateNotesList(notes: List<Note>) {
        val adapter = recyclerview.adapter as NotesListAdapter
        adapter.setNotes(notes)

        Snackbar
            .make(fab, "Notes list updated", Snackbar.LENGTH_LONG)
            .show()
    }
}
