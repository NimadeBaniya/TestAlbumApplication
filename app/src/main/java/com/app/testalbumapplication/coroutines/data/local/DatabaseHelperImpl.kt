package com.app.testalbumapplication.coroutines.data.local
import com.app.testalbumapplication.coroutines.data.model.Albums


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAlbumsDB(): List<Albums> {
       return appDatabase.albumDao().getAll()
    }

    override suspend fun insertAllDB(albums: List<Albums>) {
        return appDatabase.albumDao().insertAll(albums)
    }


}