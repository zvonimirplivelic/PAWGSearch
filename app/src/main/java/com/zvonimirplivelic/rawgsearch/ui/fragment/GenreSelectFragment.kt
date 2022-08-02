package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.*
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.domain.RAWGGenre
import com.zvonimirplivelic.rawgsearch.domain.asDatabaseModel
import com.zvonimirplivelic.rawgsearch.ui.adapter.GenreListAdapter
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModel
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModelFactory
import kotlinx.coroutines.launch
import timber.log.Timber


class GenreSelectFragment : Fragment(), GenreListAdapter.CheckBoxCallback {

    private val viewModel: RAWGSearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            RAWGSearchViewModelFactory(activity.application)
        )[RAWGSearchViewModel::class.java]
    }

    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSelectGenres: Button
    private lateinit var genreList: List<RAWGGenre>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_genre_select, null, false)

        recyclerView = view.findViewById(R.id.rv_genre_list)
        btnSelectGenres = view.findViewById(R.id.btn_select_genres)

        setupRecyclerView()

        viewModel.genres.observe(viewLifecycleOwner) { genreList ->
            this.genreList = genreList
            genreListAdapter.setData(genreList)
        }

        btnSelectGenres.setOnClickListener {
            lifecycleScope.launch {
                handleGenreData(genreList)
                Timber.d("$genreList")
            }

            val navArray: Array<RAWGGenre> = genreList.toTypedArray()
            val action =
                GenreSelectFragmentDirections.actionGenreSelectFragmentToGamesListFragment(
                    navArray
                )
            findNavController().navigate(action)
        }

        return view
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter(this)
        recyclerView.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override suspend fun handleGenreData(genreData: List<RAWGGenre>) {
        viewModel.storeSelectedGenres(genreList.asDatabaseModel())

    }
}