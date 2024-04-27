package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.NoteApplication
import com.eseo.noteapp.R
import com.eseo.noteapp.databinding.ActivityUpdateBinding
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.viewmodel.NoteViewModel
import com.eseo.noteapp.viewmodel.NoteViewModelFactory
//Récupère les données de la note à mettre à jour à partir de l'intent qui a lancé cette activité.
class UpdateActivity : AppCompatActivity() {
    //Utilise viewModels delegate pour obtenir une instance du NoteViewModel.
    private lateinit var binding: ActivityUpdateBinding

    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }
//permet la mise a jour de la note selectioné
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val replyIntent = Intent()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
/*
 Récupère les données de la note à mettre à jour à partir de l'intent.
 Affiche les données de la note dans les champs d'édition du layout
*/
        val note : Note = intent.getSerializableExtra("UPDATE") as Note
        binding.titleEdit.setText(note.title)
        binding.textEdit.setText(note.text)
//Gestion du clic sur le bouton de confirmation
        binding.confirmButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
//mise a jour de la BD
                note.title = binding.titleEdit.text.toString()
                note.text = binding.textEdit.text.toString()
                noteViewModel.updateNote(note)
                replyIntent.putExtra(AddActivity.EXTRA_REPLY, note)
                setResult(Activity.RESULT_OK, replyIntent)

            }
            finish()
        }





    }
}