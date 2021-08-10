package com.app.testalbumapplication.coroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.app.testalbumapplication.coroutines.data.model.Albums
import com.app.testalbumapplication.coroutines.ui.main.adapter.MainAdapter.DataViewHolder
import com.app.testalbumapplication.databinding.ItemLayoutBinding


class MainAdapter(private val albums: ArrayList<Albums>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(val binding:ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(albums: Albums) {
            itemView.apply {
                binding.textViewTitle.text = albums.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding=ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }
    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun addAlbums(albums: List<Albums>) {
        this.albums.apply {
            clear()
            addAll(albums)
        }

    }
}