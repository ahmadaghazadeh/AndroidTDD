package com.arad.androidtdd.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }

}
