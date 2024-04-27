package com.eseo.noteapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.model.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/*
* ce ViewModel agit comme un pont entre la couche de présentation (UI) et la couche d'accès aux
* données (repository). Il fournit des méthodes pour interagir avec les données des notes, tout
* en garantissant une gestion appropriée des threads et en fournissant des données observables aux
* composants d'interface utilisateur.
* */
class NoteViewModel(
    private val noteRepository : NoteRepository
) : ViewModel() {
//permet détecter les changements et mettre à jour l'interface en conséquence.
    val notesLiveData : LiveData<List<Note>> = noteRepository.notes
//cela garantit que les opérations sont exécutées dans le contexte approprié.
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insertNote(note)
    }

    fun deleteNote(id : Int) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(id)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateNote(note)
    }

}