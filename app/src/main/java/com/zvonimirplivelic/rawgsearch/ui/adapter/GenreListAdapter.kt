package com.zvonimirplivelic.rawgsearch.ui.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.core.util.forEach
import androidx.core.util.isEmpty
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.domain.asDatabaseModel
import com.zvonimirplivelic.rawgsearch.util.DiffUtilExtension.autoNotify
import timber.log.Timber
import kotlin.properties.Delegates

class GenreListAdapter(
    private val handler: GenreListAdapter.SelectedGenresCallback
) :
    RecyclerView.Adapter<GenreListAdapter.GenreListItemViewHolder>() {

    private var genreList: List<DBGenre>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }
    var selectedGenreArray = SparseBooleanArray()

    inner class GenreListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val ctvSelectGenre: CheckedTextView

        fun bind(position: Int) {
            ctvSelectGenre.isChecked = genreList[position].isSelected
            ctvSelectGenre.text = genreList[position].name
        }

        override fun onClick(v: View?) {
            val adapterPosition = adapterPosition
            if (!selectedGenreArray[adapterPosition, false]) {
                ctvSelectGenre.isChecked = true
                selectedGenreArray.put(adapterPosition, true)
            } else {
                ctvSelectGenre.isChecked = false
                selectedGenreArray.put(adapterPosition, false)
            }
        }

        init {
            ctvSelectGenre = itemView.findViewById(R.id.ctv_select_genre)
            itemView.setOnClickListener(this)
        }
    }

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
        holder.bind(position)
    }

    override fun getItemCount(): Int = genreList.size

    fun setData(genreList: List<RAWGGenre>) {
        this.genreList = genreList.asDatabaseModel()
    }

    fun selectGenres() {
        val selectedGenreList = genreList
        val genreArray = selectedGenreArray


        genreArray.forEach { position, selectedGenre ->
            selectedGenreList[position].isSelected = selectedGenre
        }

        handler.handleSelectedGenres(selectedGenreList)
    }

    interface SelectedGenresCallback {
        fun handleSelectedGenres(selectedGenres: List<DBGenre>)
    }
}