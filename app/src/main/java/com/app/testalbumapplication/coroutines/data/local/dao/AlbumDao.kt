package com.app.testalbumapplication.coroutines.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.testalbumapplication.coroutines.data.model.Albums

@Dao
interface AlbumDao
{

    @Query("SELECT * FROM Albums")  
    suspend fun getAll(): List<Albums>

    @Insert
    suspend fun insertAll(albums: List<Albums>)
}