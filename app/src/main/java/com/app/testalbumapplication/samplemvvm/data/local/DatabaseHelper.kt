package com.app.testalbumapplication.samplemvvm.data.local


import com.app.testalbumapplication.samplemvvm.data.model.Albums

interface DatabaseHelper {

    suspend fun getAlbumsDB(): List<Albums>

    suspend fun insertAllDB(albums: List<Albums>)

}