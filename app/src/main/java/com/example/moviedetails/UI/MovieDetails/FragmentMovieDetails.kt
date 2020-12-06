package com.example.moviedetails.ui.moviedetails

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedetails.R
import com.example.moviedetails.data.DataContainer


class MovieDetailsFragment : Fragment() {
    private var movieId: Long? = null
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var actorListRecycler: RecyclerView
    private var fragmentMovieDetailsClickListener: ClickListenerFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actorList = DataContainer.
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view?.findViewById<Button>(R.id.back_to_main_button)?.setOnClickListener {
            activity?.onBackPressed()
        }

        return view

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListenerFragment) {
            fragmentMovieDetailsClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMovieDetailsClickListener = null
    }
}

interface ClickListenerFragment {
    fun toSecondFragment()
}