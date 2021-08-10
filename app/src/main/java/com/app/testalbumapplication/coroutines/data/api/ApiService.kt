package com.app.testalbumapplication.coroutines.data.api

import com.app.testalbumapplication.coroutines.data.model.Albums
import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Albums>

}