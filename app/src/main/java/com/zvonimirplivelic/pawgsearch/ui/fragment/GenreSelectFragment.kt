package com.zvonimirplivelic.pawgsearch.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.pawgsearch.*
import com.zvonimirplivelic.pawgsearch.db.DBGenre
import com.zvonimirplivelic.pawgsearch.ui.adapter.GenreListAdapter
import com.zvonimirplivelic.pawgsearch.viewmodel.PAWGSearchViewModel
import com.zvonimirplivelic.pawgsearch.viewmodel.PAWGSearchViewModelFactory


class GenreSelectFragment : Fragment() {

    private val viewModel: PAWGSearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            PAWGSearchViewModelFactory(activity.application)
        )[PAWGSearchViewModel::class.java]
    }

    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSelectGenres: Button

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
           
        }


        return view
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter()
        recyclerView.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}