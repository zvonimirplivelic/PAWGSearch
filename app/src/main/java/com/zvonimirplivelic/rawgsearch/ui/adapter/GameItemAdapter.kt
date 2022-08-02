package com.zvonimirplivelic.rawgsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.R

class GameItemAdapter: RecyclerView.Adapter<GameItemAdapter.GameListItemViewHolder>() {

    inner class GameListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 10
}