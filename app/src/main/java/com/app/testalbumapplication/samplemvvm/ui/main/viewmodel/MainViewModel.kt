package com.app.testalbumapplication.samplemvvm.ui.main.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testalbumapplication.samplemvvm.data.api.ApiHelper
import com.app.testalbumapplication.samplemvvm.data.local.DatabaseHelper
import com.app.testalbumapplication.samplemvvm.data.model.Albums
import com.app.testalbumapplication.samplemvvm.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel (private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper)  : ViewModel() {

    private val albums = MutableLiveData<Resource<List<Albums>>>()



     fun fetchAlbumsData() {
        viewModelScope.launch {
            albums.postValue(Resource.loading(null))
            try {

                val albumsFromDb = dbHelper.getAlbumsDB()

                if (albumsFromDb.isEmpty())
                {

                        val albumsFromApi = apiHelper.getAlbums()
                        val albumsToInsertInDB = mutableListOf<Albums>()

                        for (apiAlbum in albumsFromApi) {
                            val user = Albums(
                                apiAlbum.id,
                                apiAlbum.userId,
                                apiAlbum.title,

                                )
                            albumsToInsertInDB.add(user)
                        }

                        dbHelper.insertAllDB(albumsToInsertInDB)
                        albums.postValue(Resource.success(albumsToInsertInDB))

                }
                else
                {
                    albums.postValue(Resource.success(albumsFromDb))
                }


            } catch (e: Exception) {
//                albums.postValue(Resource.error("Something Went Wrong", ""))
            }
        }
    }


    fun getAlbums(): LiveData<Resource<List<Albums>>> {
        return albums
    }

    
}