package com.app.testalbumapplication.samplemvvm.data.api

import com.app.testalbumapplication.samplemvvm.data.model.Albums
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Albums>

}