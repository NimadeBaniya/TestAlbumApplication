package com.app.testalbumapplication.samplemvvm.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.testalbumapplication.samplemvvm.data.api.ApiHelperImpl
import com.app.testalbumapplication.samplemvvm.data.api.RetrofitBuilder
import com.app.testalbumapplication.samplemvvm.data.local.DatabaseBuilder
import com.app.testalbumapplication.samplemvvm.data.local.DatabaseHelperImpl
import com.app.testalbumapplication.samplemvvm.data.model.Albums
import com.app.testalbumapplication.samplemvvm.ui.base.ViewModelFactory
import com.app.testalbumapplication.samplemvvm.ui.main.adapter.MainAdapter
import com.app.testalbumapplication.samplemvvm.ui.main.viewmodel.MainViewModel
import com.app.testalbumapplication.samplemvvm.utils.Status
import com.app.testalbumapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupViewModel()
        setupUI()
        setupObserver()

    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(MainViewModel::class.java)

    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getAlbums().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users.sorted()) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(albums: List<Albums>) {
        adapter.addAlbums(albums)
        adapter.notifyDataSetChanged()
    }
}
