package com.app.testalbumapplication.samplemvvm.data.api

import com.app.testalbumapplication.samplemvvm.data.model.Albums

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper
{
    override suspend fun getAlbums(): List<Albums> {
        return apiService.getAlbums()
    }


}