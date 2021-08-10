package com.app.testalbumapplication.coroutines.ui.main.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testalbumapplication.coroutines.data.api.ApiHelper
import com.app.testalbumapplication.coroutines.data.local.DatabaseHelper
import com.app.testalbumapplication.coroutines.data.model.Albums
import com.app.testalbumapplication.coroutines.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel (private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper)  : ViewModel() {

    private val albums = MutableLiveData<Resource<List<Albums>>>()
    private var isInternet:Boolean = false

    init {

        fetchAlbumsData()
    }

    private fun fetchAlbumsData() {
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

            }
        }
    }

    fun getAlbums(): LiveData<Resource<List<Albums>>> {
        return albums
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}