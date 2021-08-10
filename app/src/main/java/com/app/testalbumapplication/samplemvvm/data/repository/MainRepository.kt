package com.app.testalbumapplication.samplemvvm.data.repository

import com.app.testalbumapplication.samplemvvm.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAlbums() = apiHelper.getAlbums()
}