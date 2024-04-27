package com.eseo.noteapp.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.databinding.ActivityAddBinding
/* cette activité permet à l'utilisateur de saisir un titre et un texte pour créer une nouvelle note,
 puis elle renvoie cette note à l'activité appelante pour être enregistrée dans la base de données.
*/
class AddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Cela permet d'accéder facilement aux vues de mise en page définies dans le fichier XML.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add )
// Ajout d'un écouteur de clic au bouton confirmButton
        binding.confirmButton.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.titleEdit.text) || TextUtils.isEmpty(binding.textEdit.text)) {
// indiquer que l'opération s'est terminée mal.
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
// Création d'un objet Note à partir des données entrées par l'utilisateur dans les champs de titre et de texte.
                val note = Note(
                    title = binding.titleEdit.text.toString(),
                    text = binding.textEdit.text.toString()
                )
// Ajout de l'objet Note comme extra à l'intention pour le transmettre à l'activité appelante.
                replyIntent.putExtra(EXTRA_REPLY, note)
// indiquer que l'opération s'est terminée avec succès.
                setResult(Activity.RESULT_OK, replyIntent)

            }
// Fermeture de l'activité et retour à l'activité appelante.
            finish()
        }
    }


    companion object {
        val EXTRA_REPLY: String = "com.eseo.android.noteapp.REPLY"
    }
}