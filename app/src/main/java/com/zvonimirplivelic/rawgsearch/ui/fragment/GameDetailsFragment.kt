package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.util.ResizeImages.setPictureHeight
import com.zvonimirplivelic.rawgsearch.util.ResizeImages.setPictureWidth

class GameDetailsFragment : Fragment() {

    private val args by navArgs<GameDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_details, container, false)

        val selectedGame = args.selectedGame

        val ivGameCover: ImageView = view.findViewById(R.id.iv_game_cover_detail)
        val tvGameName: TextView = view.findViewById(R.id.tv_game_name_detail)
        val tvGamePlaytime: TextView = view.findViewById(R.id.tv_game_playtime_detail)
        val tvGameReleasedDate: TextView = view.findViewById(R.id.tv_game_released_date_detail)
        val tvGameRating: TextView = view.findViewById(R.id.tv_game_rating_detail)
        val tvGameEsrbRating: TextView = view.findViewById(R.id.tv_esrb_rating_detail)

        Picasso.get().load(selectedGame.background_image).placeholder(R.drawable.ic_filter)
            .resize(setPictureWidth(), setPictureHeight())
            .centerCrop()
            .noFade().into(ivGameCover)
        tvGameName.text = selectedGame.name
        tvGamePlaytime.text = selectedGame.playtime.toString()
        tvGameReleasedDate.text = selectedGame.released
        tvGameRating.text = selectedGame.rating.toString()
        tvGameEsrbRating.text = selectedGame.esrb_rating?.name


        return view
    }
}