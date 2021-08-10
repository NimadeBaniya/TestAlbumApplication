package com.app.testalbumapplication.samplemvvm.ui.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.testalbumapplication.samplemvvm.data.api.ApiHelper
import com.app.testalbumapplication.samplemvvm.data.local.DatabaseHelper
import com.app.testalbumapplication.samplemvvm.ui.main.viewmodel.MainViewModel


class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper,dbHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

