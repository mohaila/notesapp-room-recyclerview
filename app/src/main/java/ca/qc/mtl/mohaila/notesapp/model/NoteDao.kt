package ca.qc.mtl.mohaila.notesapp.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("delete from notes")
    fun deleteAll()

    @Query("select * from notes order by urgent desc")
    fun getAll() : LiveData<List<Note>>
}