package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import timber.log.Timber

class GamesListFragment : Fragment() {

    private val args by navArgs<GamesListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games_list, container, false)

        Timber.d("${args.selectedGenre?.get(0)?.isSelected}")

        return view
    }
}