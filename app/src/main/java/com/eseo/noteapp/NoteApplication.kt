package com.eseo.noteapp

import android.app.Application
import com.eseo.noteapp.model.NoteDatabase
import com.eseo.noteapp.model.repository.NoteRepository
/*
 Elle est utilisée pour initialiser et fournir des instances partagées de la base de données et
 du référentiel (repository) des notes à différentes parties de l'application.
*/
class NoteApplication : Application() {
//La base de données comme le repository sont initialisées que lorsque cette propriété sera accédée pour la première fois.
    val database by lazy { NoteDatabase.getNoteDatabase(this) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}