package com.eseo.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eseo.noteapp.model.repository.NoteRepository
/*
* cette classe NoteViewModelFactory agit comme une fabrique (factory) pour créer des instances
* du ViewModel NoteViewModel. Elle permet de fournir au ViewModel toutes ses dépendances nécessaires,
* en particulier le NoteRepository*/
class NoteViewModelFactory(
    private val noteRepository : NoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}