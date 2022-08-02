package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.ui.adapter.GameItemAdapter
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameItemAdapter: GameItemAdapter


    private var queryString: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_games_list, container, false)



        lifecycleScope.launch {
            val deferred = async {
                viewModel.selectedGenres.observe(viewLifecycleOwner) { genreList ->
                    queryString = genreList.joinToString { it.slug }.replace(" ", "")
                    Timber.d("Querybuilder $queryString")
                }

            }
            deferred.await()

            withContext(Dispatchers.IO) {
                delay(1000L)
                Timber.d("Querybuilder $queryString")
                viewModel.getGameList(API_KEY, queryString)
            }
        }

        viewModel.gamesList.observe(viewLifecycleOwner) { gameDataResponse ->
            if (gameDataResponse.isSuccessful) {
                Timber.d("ResultBuilder ${gameDataResponse.body()!!.results}")
            } else {
                gameDataResponse.errorBody()
            }
        }

        return view
    }
}