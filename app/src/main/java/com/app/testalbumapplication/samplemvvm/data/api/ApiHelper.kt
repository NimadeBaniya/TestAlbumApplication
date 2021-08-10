package com.app.testalbumapplication.samplemvvm.data.api

import com.app.testalbumapplication.samplemvvm.data.model.Albums

interface ApiHelper
{

    suspend fun getAlbums() :List<Albums>
}