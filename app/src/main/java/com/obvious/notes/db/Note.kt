package com.obvious.notes.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity(tableName = "note_table")
@Parcelize
data class Note(@ColumnInfo(name = "title") var title: String,
                @ColumnInfo(name = "description") var description : String,
                @ColumnInfo(name = "Time")
                val createdTime: Date = Date()) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}