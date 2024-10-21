package com.wahyuzul.androidretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wahyuzul.androidretrofit.databinding.AdapterMovieBinding

class MainAdapter(val results : ArrayList<MainModel.Result>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private lateinit var binding: AdapterMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val result = results[position]
        holder.binding.textView.text = result.title
    }

    override fun getItemCount() = results.size

    class ViewHolder (val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<MainModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

}