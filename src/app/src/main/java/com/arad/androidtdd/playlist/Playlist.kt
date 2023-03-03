package com.arad.androidtdd.playlist

import com.arad.androidtdd.R

data class Playlist(
val id:String,
val name: String,
val category: String,
val image:Int= R.mipmap.playlist
)

