package com.arad.androidtdd.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.arad.androidtdd.R
import com.arad.androidtdd.databinding.FragmentItemListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel
    private lateinit var binding: FragmentItemListBinding
    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(layoutInflater)
        val view = binding.root
        setUpViewModel()


        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> binding.loader.visibility = View.VISIBLE
                else -> binding.loader.visibility = View.GONE
            }
        }

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if(playlists.getOrNull()!=null){
                setupList(view, playlists.getOrNull()!!)
            }
            else{
                //TODO:
            }
        }
        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = PlaylistRecyclerViewAdapter(playlists)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance( ) =
            PlaylistFragment().apply {

            }
    }
}