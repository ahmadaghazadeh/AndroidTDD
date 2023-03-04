package com.arad.androidtdd.playlist

import retrofit2.http.GET

interface PlayListAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists():List<Playlist>

}
