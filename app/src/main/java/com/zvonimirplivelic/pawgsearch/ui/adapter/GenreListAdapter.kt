package com.zvonimirplivelic.pawgsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.pawgsearch.R
import com.zvonimirplivelic.pawgsearch.model.genre.Genre

class GenreListAdapter :
    RecyclerView.Adapter<GenreListAdapter.GenreListItemViewHolder>() {

    inner class GenreListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback =
        object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(
                oldItem: Genre,
                newItem: Genre
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Genre,
                newItem: Genre
            ): Boolean {
                return oldItem == newItem
            }
        }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListItemViewHolder {
        return GenreListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.genre_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreListItemViewHolder, position: Int) {
        val genre = differ.currentList[position]

        holder.itemView.apply {
            val tvGenreName: TextView = findViewById(R.id.tv_genre_name)
            val cbGenreSelect: CheckBox = findViewById(R.id.cb_genre_select)

            tvGenreName.text = genre.name
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}