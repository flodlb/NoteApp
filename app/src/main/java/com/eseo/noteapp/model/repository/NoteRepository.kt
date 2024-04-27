package com.eseo.noteapp.model.repository
import androidx.lifecycle.LiveData
import com.eseo.noteapp.model.dao.NoteDao
import com.eseo.noteapp.model.entity.Note
/*
 Un Repository agit comme un pont entre les sources de données, telles que la base de données,
 et les couches supérieures de l'application, comme le ViewModel. Il permet d'encapsuler la logique
 d'accès aux données et de fournir une interface unifiée pour récupérer et manipuler les données.
*/
class NoteRepository(
    private val noteDao: NoteDao
) {
//Récupère toutes les notes dans la base de donné
    val notes : LiveData<List<Note>> = noteDao.getAllNotes()
//Insère une note dans la BD
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }
//Supprimer une note dans la BD 
    suspend fun deleteNote(id : Int){
        noteDao.deleteNote(id)
    }
//Met à jour des notes
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

}
