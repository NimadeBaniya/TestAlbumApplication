package com.app.testalbumapplication.coroutines.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.testalbumapplication.coroutines.data.api.ApiHelper
import com.app.testalbumapplication.coroutines.data.local.DatabaseHelper
import com.app.testalbumapplication.coroutines.ui.main.viewmodel.MainViewModel


class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper,dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

