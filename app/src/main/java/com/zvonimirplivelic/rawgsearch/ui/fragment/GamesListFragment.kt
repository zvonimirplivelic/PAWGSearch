package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.util.Constants.API_KEY
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModel
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModelFactory
import kotlinx.coroutines.*
import timber.log.Timber

class GamesListFragment : Fragment() {

    private val viewModel: RAWGSearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            RAWGSearchViewModelFactory(activity.application)
        )[RAWGSearchViewModel::class.java]
    }

    private var queryString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games_list, container, false)

        lifecycleScope.launch {
            val deffered = async {
                viewModel.selectedGenres.observe(viewLifecycleOwner) { genreList ->
                    queryString = genreList.joinToString { it.slug }.replace(" ", "")
                }

                withContext(Dispatchers.IO) {
                    viewModel.getGameList(API_KEY, queryString)
                }
                delay(1000)
            }
            deffered.await()
        }
                viewModel.gamesList.observe(viewLifecycleOwner) { gameDataResponse ->
                    if (gameDataResponse.isSuccessful) {
                        Timber.d("${gameDataResponse.body()}")
                    } else {
                        gameDataResponse.errorBody()
                    }
                }

        return view
    }
}