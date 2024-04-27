package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityNoteBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.adapter.NoteAdapter
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory

/*
 Elle est responsable de l'affichage de la liste des notes et de la gestion des interactions
 utilisateur pour créer de nouvelles notes.
*/
class NoteActivity : AppCompatActivity() {
    /*
     Déclare des propriétés privées pour le code de demande de nouvelle activité,
     le ViewModel de note, l'objet de liaison de données et l'adaptateur de note.
    */
    private val newNoteActivityRequestCode = 1
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }
    private lateinit var binding: ActivityNoteBinding
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note)
// Initialise l'adaptateur de note, configure le RecyclerView pour afficher les notes
        noteAdapter = NoteAdapter(noteViewModel)

        setNoteRecycler()
//observe les changements dans les données des notes à l'aide d'un observeur attaché au LiveData
        noteViewModel.notesLiveData.observe(this, Observer { notes ->
            noteAdapter.loadNotes(notes)
        })
/*
 écouteur de clic sur le bouton "Ajouter une nouvelle note" pour
 lancer l'activité AddActivity lorsque le bouton est cliqué.
 */
        binding.addNewNote.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

//Configure le RecyclerView pour afficher les notes dans une grille à deux colonnes.
    private fun setNoteRecycler() {
        binding.noteRecycler.apply {
            adapter = noteAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
            setHasFixedSize(true)
        }
    }
/*
 Redéfinit la méthode onActivityResult() pour gérer les résultats renvoyés par l'activité
 AddActivity. Si une nouvelle note est ajoutée avec succès, elle est extraite de l'intention
 retournée et insérée dans la base de données via le ViewModel. Si l'opération échoue, un message
 d'erreur est affiché à l'utilisateur.
*/
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {

            val note =  intentData?.getSerializableExtra(AddActivity.EXTRA_REPLY) as Note
            noteViewModel.insertNote(note)

        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}