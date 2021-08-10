package com.app.testalbumapplication.samplemvvm.data.local
import com.app.testalbumapplication.samplemvvm.data.model.Albums


class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override suspend fun getAlbumsDB(): List<Albums> {
       return appDatabase.albumDao().getAll()
    }

    override suspend fun insertAllDB(albums: List<Albums>) {
        return appDatabase.albumDao().insertAll(albums)
    }


}