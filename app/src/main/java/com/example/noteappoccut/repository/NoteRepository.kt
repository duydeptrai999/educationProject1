package com.example.noteappoccut.repository

import androidx.room.Query
import com.example.noteappoccut.database.NoteDatabase
import com.example.noteappoccut.model.Note

class NoteRepository(private val db : NoteDatabase) {

    suspend fun insertNote(note :Note)=db.getNote().inserNote(note)
    suspend fun deleteNote(note :Note)=db.getNote().deleteNote(note)
    suspend fun updateNote(note :Note)=db.getNote().updateNote(note)

    fun  getAllNotes()= db.getNote().getAllNotes()
    fun searchNote (query: String?) = db.getNote().searchNote(query)
}