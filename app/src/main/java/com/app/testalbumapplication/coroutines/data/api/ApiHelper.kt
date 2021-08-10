package com.app.testalbumapplication.coroutines.data.api

import com.app.testalbumapplication.coroutines.data.model.Albums

interface ApiHelper
{

    suspend fun getAlbums() :List<Albums>
}