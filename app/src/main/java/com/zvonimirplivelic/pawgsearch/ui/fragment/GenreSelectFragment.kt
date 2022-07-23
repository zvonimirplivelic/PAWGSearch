package com.zvonimirplivelic.pawgsearch.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.pawgsearch.PAWGSearchViewModel
import com.zvonimirplivelic.pawgsearch.R
import com.zvonimirplivelic.pawgsearch.ui.adapter.GenreListAdapter
import com.zvonimirplivelic.pawgsearch.util.Resource
import timber.log.Timber


class GenreSelectFragment : Fragment() {

    private lateinit var viewModel: PAWGSearchViewModel
    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var recyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar
    private lateinit var btnSelectGenres: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_genre_select, container, false)

        viewModel = ViewModelProvider(this)[PAWGSearchViewModel::class.java]

        recyclerView = view.findViewById(R.id.rv_genre_list)
        progressBar = view.findViewById(R.id.progress_bar)
        btnSelectGenres = view.findViewById(R.id.btn_select_genres)

        genreListAdapter = GenreListAdapter()

        recyclerView.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.getGenreList()

        viewModel.genreList.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    progressBar.isVisible = false

                    response.data?.let { genreListResponse ->
                        Timber.d("GList: $genreListResponse")
                        genreListAdapter.setData(genreListResponse.genreList)
                    }
                }

                is Resource.Error -> {
                    progressBar.isVisible = false

                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    progressBar.isVisible = true
                }
            }

        }


//        btnSelectGenres.setOnClickListener {
//
//        }
        return view
    }
}