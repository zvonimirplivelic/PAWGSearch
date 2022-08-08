package com.zvonimirplivelic.rawgsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.rawgsearch.R
import com.zvonimirplivelic.rawgsearch.db.DBGenre
import com.zvonimirplivelic.rawgsearch.ui.adapter.GenreListAdapter
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModel
import com.zvonimirplivelic.rawgsearch.viewmodel.RAWGSearchViewModelFactory
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


class GenreSelectFragment : Fragment(), GenreListAdapter.SelectedGenresCallback {

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
    private lateinit var selectedGenreList: List<DBGenre>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_genre_select, null, false)

        recyclerView = view.findViewById(R.id.rv_genre_list)
        btnSelectGenres = view.findViewById(R.id.btn_select_genres)

        setupRecyclerView()

        viewModel.genres.observe(viewLifecycleOwner) { genreList ->
            genreListAdapter.setData(genreList)
        }

        btnSelectGenres.setOnClickListener {
            genreListAdapter.selectGenres()

            var selectedGenreCount = 0
            val navArray: Array<DBGenre> = selectedGenreList.toTypedArray()

            Timber.d("GenCountNA: ${navArray.size}")
            lifecycleScope.launch {
                delay(300)
                val deferred = async {
                    selectedGenreCount = genreCount(navArray)
                    viewModel.updateGenres(selectedGenreList)
                }
                deferred.await()

                navigateToGameScreen(selectedGenreCount, navArray)
            }
        }

        return view
    }

    private fun navigateToGameScreen(
        selectedGenreCount: Int,
        navArray: Array<DBGenre>
    ) {
        if (selectedGenreCount != 0) {
            Timber.d("GenCount: $selectedGenreCount")
            val action =
                GenreSelectFragmentDirections.actionGenreSelectFragmentToGamesListFragment(
                    navArray
                )
            findNavController().navigate(action)
        } else {
            Timber.d("GenCount: $selectedGenreCount")
            Toast.makeText(
                requireContext(),
                "Please select at least one genre",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun genreCount(navArray: Array<DBGenre>): Int {
        return navArray.count { it.isSelected }
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter(this)
        recyclerView.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun handleSelectedGenres(selectedGenres: List<DBGenre>) {
        this.selectedGenreList = selectedGenres
    }
}