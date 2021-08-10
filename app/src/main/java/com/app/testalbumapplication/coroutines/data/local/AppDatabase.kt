package com.app.testalbumapplication.coroutines.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.testalbumapplication.coroutines.data.local.dao.AlbumDao
import com.app.testalbumapplication.coroutines.data.model.Albums

@Database(entities = [Albums::class], version = 1)
abstract  class AppDatabase : RoomDatabase()
{
    abstract fun albumDao(): AlbumDao
}