package com.app.testalbumapplication.samplemvvm.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.testalbumapplication.samplemvvm.data.local.dao.AlbumDao
import com.app.testalbumapplication.samplemvvm.data.model.Albums

@Database(entities = [Albums::class], version = 1)
abstract  class AppDatabase : RoomDatabase()
{
    abstract fun albumDao(): AlbumDao
}