package com.arad.androidtdd.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PlaylistViewModel(
    private val repository: PlaylistRepository
):ViewModel() {
    val loader=MutableLiveData<Boolean>()

    val playlists= liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }

}
