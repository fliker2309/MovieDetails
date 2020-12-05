package com.example.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.data.DataContainer
import com.example.moviedetails.data.Movie

class MovieListFragment : Fragment() {


    private var movieListRecycler: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val movieList = DataContainer.getAllMovies()
        val numberOfColums = 2
        val movieListAdapter = MovieListAdapter(
            cardListener = onMovieLabelClick()
        )
        val gridLayoutManager = GridLayoutManager(context,2)

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)


        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()

    }

    companion object {
        fun newInstance() = MovieListFragment()

        const val TAG = "moviesListFragment"
    }

   private fun onMovieLabelClick(){

   }
}
