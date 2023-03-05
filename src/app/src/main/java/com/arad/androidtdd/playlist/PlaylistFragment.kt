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
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        setUpViewModel()

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