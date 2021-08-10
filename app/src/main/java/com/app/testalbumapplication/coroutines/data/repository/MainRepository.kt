package com.app.testalbumapplication.coroutines.data.repository

import com.app.testalbumapplication.coroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAlbums() = apiHelper.getAlbums()
}