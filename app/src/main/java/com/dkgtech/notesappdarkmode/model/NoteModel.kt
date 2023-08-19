package com.dkgtech.notesappdarkmode.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val subtitle: String,
    val note: String,
    val date: String,
    val priority: String
) : Parcelable