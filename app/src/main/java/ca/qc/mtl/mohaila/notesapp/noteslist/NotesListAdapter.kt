package ca.qc.mtl.mohaila.notesapp.noteslist

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import ca.qc.mtl.mohaila.notesapp.R
import ca.qc.mtl.mohaila.notesapp.model.Note

class NotesListAdapter(private val presenter: NotesListContract.Presenter) : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {
    private var notes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes [position]
        holder.noteName.text = note.name
        holder.noteDesc.text = note.description
        if(note.urgent)
            holder.noteIcon.setImageResource(R.drawable.red_ball)
        else
            holder.noteIcon.setImageResource(R.drawable.green_ball)
    }

    fun setNotes(notes: List<Note>) {
        this.notes = ArrayList(notes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteName = itemView.findViewById<TextView>(R.id.note_name)
        val noteDesc = itemView.findViewById<TextView>(R.id.note_desc)
        val noteIcon = itemView.findViewById<ImageView>(R.id.note_icon)
        val editButton = itemView.findViewById<ImageButton>(R.id.edit_button)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.delete_button)
        val cardView = itemView.findViewById<CardView>(R.id.cardview)

        init {
            cardView.setOnClickListener {
                val note = notes[adapterPosition]
                val id = note.id
                val name = note.name
                val description = note.description
                val urgent = !note.urgent
                presenter.updateNote(Note(id, name, description, urgent))
            }

            editButton.setOnClickListener {
                val note = notes[adapterPosition]
                presenter.startEditNoteActivity(note)
            }

            deleteButton.setOnClickListener {
                val note = notes[adapterPosition]
                presenter.deleteNote(note)
            }
        }
    }
}