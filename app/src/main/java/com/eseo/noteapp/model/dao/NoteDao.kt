package com.eseo.noteapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eseo.noteapp.model.entity.Note

/*Cette interface, sert d'intermédiaire entre votre application Android et la base
 de données SQLite. Elle définit un ensemble de méthodes qui permettent à votre application
 d'effectuer des opérations CRUD (Create, Read, Update, Delete)
*/
//@Dao permet de déclarer cette interface comme un Data Access Object.
@Dao
interface NoteDao {
    /*permet de dire que la méthode permet d'inserer dans la base de données des données sous forme Note*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    /*permet de mettre les données de la base de données dans une liste de type note*/
    @Query("SELECT * FROM note")
    fun getAllNotes() : LiveData<List<Note>>
    /*permet de supprimer une note dans la base de données*/
    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNote(id : Int)
    /*permet de mettre à jour une note dans la base de données*/
    @Update(
        entity = Note::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun updateNote(note: Note)

}


