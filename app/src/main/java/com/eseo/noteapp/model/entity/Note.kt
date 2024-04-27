package com.eseo.noteapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
/*c'est le format d'enregistrement des données dans la base de données*/
@Entity(tableName = "note")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "text")
    var text: String
) : Serializable
