package com.eseo.noteapp.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eseo.noteapp.R
import com.eseo.noteapp.model.entity.Note
import com.eseo.noteapp.view.activity.UpdateActivity
import com.eseo.noteapp.viewmodel.NoteViewModel

/*
 cette classe facilite l'affichage et la mise à jour des données des notes dans un RecyclerView, en
 gérant la création de ViewHolders, la liaison des données et la gestion des interactions utilisateur.
 Cet adaptateur est responsable de l'affichage des données de la liste de notes dans un RecyclerView.
*/
class NoteAdapter ( private val noteViewModel: NoteViewModel) :
                    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
//Cette liste contiendra les données des notes à afficher dans le RecyclerView.
    private var notes : MutableList<Note> = mutableListOf()


// Cette méthode crée une vue de note en inflatant le layout note_item à partir du parent,
// puis crée et retourne un nouvel objet NoteViewHolder pour cette vue.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(noteView)
    }

//Méthode permettant de lier les données des notes à une vue ViewHolder spécifique.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindNote(notes[position])
    }
//C'est la longueur de la liste notes
    override fun getItemCount(): Int {
        return notes.size
    }

//Annotation supprimant les avertissements du linter liés à l'appel de notifyDataSetChanged()
    @SuppressLint("NotifyDataSetChanged")
/*
 Déclaration de la fonction loadNotes qui charge les données des notes dans l'adaptateur.
 Cette fonction prend une liste de notes en paramètre et la met à jour dans la propriété
 notes de l'adaptateur. Ensuite, elle appelle notifyDataSetChanged() pour informer RecyclerView
 que les données ont changé et que la vue doit être mise à jour.
*/
    fun loadNotes(notes: List<Note>?) {
        this.notes = notes as MutableList<Note>
        notifyDataSetChanged()
    }

// Cette classe représente la vue pour chaque élément individuel dans le RecyclerView.
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
// les vues de titre de note, de texte de note, d'image de modification et d'image de suppression
// sont initialisées en recherchant les éléments correspondants dans la vue de l'élément de note (itemView)
// à l'aide de findViewById().
        private var noteTitle : TextView
        private var noteText : TextView
        private var editNoteImage : ImageView
        private var deleteNoteImage : ImageView

        init {
            noteTitle = itemView.findViewById(R.id.noteTitle)
            noteText = itemView.findViewById(R.id.noteText)
            editNoteImage = itemView.findViewById(R.id.editNoteImage)
            deleteNoteImage = itemView.findViewById(R.id.deleteNoteImage)
        }
// Elle prend un objet Note en paramètre et utilise ses propriétés pour définir le texte des vues
// de titre et de texte de la note.
        fun bindNote(note: Note){
            noteText.text = note.text
            noteTitle.text = note.title
    //gere le bouton update
            editNoteImage.setOnClickListener {
                val context : Context = itemView.context
                val noteIntent = Intent(context, UpdateActivity::class.java)
                noteIntent.putExtra("UPDATE", note)
                context.startActivity(noteIntent)
            }
    //gere le bouton delete
            deleteNoteImage.setOnClickListener {
                noteViewModel.deleteNote(note.id)
            }
        }
    }
}

