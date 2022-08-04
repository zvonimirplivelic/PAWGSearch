package com.zvonimirplivelic.rawgsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.remote.model.games.GameListResult
import com.zvonimirplivelic.rawgsearch.util.DiffUtilExtension.autoNotify
import kotlin.properties.Delegates

class GameListAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<GameListAdapter.GameListItemViewHolder>() {

    var gamesList: List<GameListResult>
            by Delegates.observable(emptyList()) { _, oldList, newList ->
                autoNotify(oldList, newList) { o, n -> o.id == n.id }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListItemViewHolder {
        return GameListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.game_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameListItemViewHolder, position: Int) {
        val game = gamesList[position]

        holder.itemView.apply {
            val ivGameCover: ImageView = findViewById(R.id.iv_game_cover)
            val tvGameName: TextView = findViewById(R.id.tv_game_name)
            val tvGameRating: TextView = findViewById(R.id.tv_game_rating)

            Picasso.get()
                .load(game.background_image)
                .resize(256, 256)
                .into(ivGameCover)

            tvGameName.text = game.name
            tvGameRating.text = game.rating.toString()
        }
    }

    override fun getItemCount(): Int = gamesList.size

    fun setData(gamesList: List<GameListResult>) {
        this.gamesList = gamesList
        notifyDataSetChanged()
    }

    inner class GameListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}