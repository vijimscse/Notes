package com.obvious.notes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "description") var description : String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}