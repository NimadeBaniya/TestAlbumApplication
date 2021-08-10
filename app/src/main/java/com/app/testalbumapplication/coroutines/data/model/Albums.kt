package com.app.testalbumapplication.coroutines.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Albums
    (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title") val title: String
):Comparable<Albums> {
    override fun compareTo(other: Albums): Int {
        return this.title.compareTo(other.title)
    }
}