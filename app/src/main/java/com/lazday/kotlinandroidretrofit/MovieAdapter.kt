package com.lazday.kotlinandroidretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lazday.kotlinandroidretrofit.databinding.AdapterMovieBinding

class MovieAdapter (
    private var results: ArrayList<MainModel.Result>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.binding.textView.text = result.title
        Glide.with(holder.binding.root)
            .load(result.image)
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .centerCrop()
            .into(holder.binding.imageView)
        holder.binding.root.setOnClickListener { listener.onClick(result) }
    }

    class ViewHolder(val binding: AdapterMovieBinding): RecyclerView.ViewHolder(binding.root)

    fun setData(data: List<MainModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }

    fun updateItem(position: Int, newResult: MainModel.Result) {
        this.results[position] = newResult
        notifyItemChanged(position)
    }
}
