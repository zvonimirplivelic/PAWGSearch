package com.zvonimirplivelic.pawgsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.pawgsearch.*
import com.zvonimirplivelic.pawgsearch.db.PAWGSearchDatabase
import com.zvonimirplivelic.pawgsearch.ui.adapter.GenreListAdapter
import kotlinx.coroutines.launch


class GenreSelectFragment : Fragment() {

    private lateinit var viewModel: PAWGSearchViewModel
    private lateinit var viewModelFactory: PAWGSearchViewModelFactory

    private lateinit var pawgSearchRepository: PAWGSearchRepository

    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var btnSelectGenres: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_genre_select, container, false)



        recyclerView = view.findViewById(R.id.rv_genre_list)
        progressBar = view.findViewById(R.id.progress_bar)
        btnSelectGenres = view.findViewById(R.id.btn_select_genres)

        genreListAdapter = GenreListAdapter()

        recyclerView.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        lifecycleScope.launch {
            viewModel.genreGenerate()
        }

        viewModel.displayGenres.observe(viewLifecycleOwner) { genreList ->
            genreListAdapter.setData(genreList)
        }


//        btnSelectGenres.setOnClickListener {
//
//        }
        return view
    }
}