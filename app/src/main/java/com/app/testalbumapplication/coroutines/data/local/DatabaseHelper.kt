package com.app.testalbumapplication.coroutines.data.local


import com.app.testalbumapplication.coroutines.data.model.Albums

interface DatabaseHelper {

    suspend fun getAlbumsDB(): List<Albums>

    suspend fun insertAllDB(albums: List<Albums>)

}