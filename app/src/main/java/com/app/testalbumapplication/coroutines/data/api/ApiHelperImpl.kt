package com.app.testalbumapplication.coroutines.data.api

import com.app.testalbumapplication.coroutines.data.model.Albums

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper
{
    override suspend fun getAlbums(): List<Albums> {
        return apiService.getAlbums()
    }


}