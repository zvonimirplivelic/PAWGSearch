package com.zvonimirplivelic.pawgsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.pawgsearch.R
import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.domain.PAWGGenre
import com.zvonimirplivelic.pawgsearch.remote.model.genre.Genre
import com.zvonimirplivelic.pawgsearch.util.DiffUtilExtension.autoNotify
import kotlin.properties.Delegates

class GenreListAdapter :
    RecyclerView.Adapter<GenreListAdapter.GenreListItemViewHolder>() {

    private var genreList: List<PAWGGenre>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }

    inner class GenreListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

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
        val genre = genreList[position]

        holder.itemView.apply {
            val tvGenreName: TextView = findViewById(R.id.tv_genre_name)
            val cbGenreSelect: CheckBox = findViewById(R.id.cb_genre_select)

            tvGenreName.text = genre.name
            cbGenreSelect.isChecked = genre.isSelected!!
        }
    }

    override fun getItemCount(): Int = genreList.size

    fun setData(genreList: List<PAWGGenre>) {
        this.genreList = genreList
        notifyDataSetChanged()
    }
}