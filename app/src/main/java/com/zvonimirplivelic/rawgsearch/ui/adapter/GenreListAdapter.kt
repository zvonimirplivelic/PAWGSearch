package com.zvonimirplivelic.rawgsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.db.SelectedGenre
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.domain.asSelectedGenre
import com.zvonimirplivelic.rawgsearch.util.DiffUtilExtension.autoNotify
import timber.log.Timber
import kotlin.properties.Delegates

class GenreListAdapter(
    val handler: GenreListAdapter.CheckBoxCallback
) :
    RecyclerView.Adapter<GenreListAdapter.GenreListItemViewHolder>() {

    private var genreList: List<RAWGGenre>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }

    private val selectedGenreList: List<SelectedGenre> = genreList.asSelectedGenre()

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
        val selectedGenre = genreList[position]

        holder.itemView.apply {
            val cvGenreCard: CardView = findViewById(R.id.cv_genre_card)
            val tvGenreName: TextView = findViewById(R.id.tv_genre_name)
            val cbGenreSelect: CheckBox = findViewById(R.id.cb_genre_select)


            tvGenreName.text = selectedGenre.name
            cbGenreSelect.isChecked = selectedGenre.isSelected!!

            cvGenreCard.setOnClickListener{
                cbGenreSelect.isChecked = !cbGenreSelect.isChecked
            }

            cbGenreSelect.setOnCheckedChangeListener { _, isChecked ->
                selectedGenre.isSelected = isChecked
                Timber.d("CBState ${selectedGenre.name}; ${selectedGenre.isSelected}; ${cbGenreSelect.isChecked}")
            }
        }
    }

    override fun getItemCount(): Int = genreList.size

    fun setData(genreList: List<RAWGGenre>) {
        this.genreList = genreList
        notifyDataSetChanged()
    }

    private suspend fun handleGenreData(genreList: List<SelectedGenre>) {
        handler.handleGenreData(selectedGenreList)
    }

    interface CheckBoxCallback {
        suspend fun handleGenreData(genreData: List<SelectedGenre>)
    }
}