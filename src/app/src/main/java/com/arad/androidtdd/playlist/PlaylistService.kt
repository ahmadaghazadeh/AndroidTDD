package com.arad.androidtdd.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(private val api:PlayListAPI) {
    suspend fun fetchPlaylists(): Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException(it.message)))
        }
    }

}
