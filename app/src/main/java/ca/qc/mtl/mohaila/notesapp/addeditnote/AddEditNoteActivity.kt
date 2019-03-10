package ca.qc.mtl.mohaila.notesapp.addeditnote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ca.qc.mtl.mohaila.notesapp.R
import ca.qc.mtl.mohaila.notesapp.model.Note
import ca.qc.mtl.mohaila.notesapp.noteslist.NotesListPresenter

import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity(), AddEditNoteContract.View {
    private lateinit var presenter: AddEditNoteContract.Presenter
    private lateinit var note: Note
    private var action = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow)

        action = intent.getIntExtra(NotesListPresenter.EXTRA_ACTION, NotesListPresenter.ACTION_ADD)
        when(action) {
            NotesListPresenter.ACTION_ADD -> title = getString(R.string.add_note_title)
            NotesListPresenter.ACTION_EDIT -> {
                title = getString(R.string.edit_note)
                note = intent.getSerializableExtra(NotesListPresenter.EXTRA_NOTE) as Note
                edit_note_name.setText(note.name)
                edit_note_desc.setText(note.description)
                sw_note_urgent.isChecked = note.urgent
            }
        }

        presenter = AddEditNotePresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.save_note -> {
                when (action) {
                    NotesListPresenter.ACTION_ADD -> presenter.saveNote()
                    NotesListPresenter.ACTION_EDIT -> {
                        presenter.updateNote(note)
                        finish()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getNoteName(): String {
        return edit_note_name.text.toString().trim()
    }

    override fun getNoteDescription(): String {
        return edit_note_desc.text.toString().trim()
    }

    override fun getNoteUrgent(): Boolean {
        return sw_note_urgent.isChecked
    }

    override fun displayMessage(id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show()
    }

    override fun clear() {
        edit_note_name.text.clear()
        edit_note_desc.text.clear()
        sw_note_urgent.isChecked = false
    }



}
