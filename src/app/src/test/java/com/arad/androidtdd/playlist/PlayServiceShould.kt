package com.arad.androidtdd.playlist

import com.arad.androidtdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.lang.RuntimeException

@OptIn(ExperimentalCoroutinesApi::class)
class PlayServiceShould:BaseUnitTest() {

    private lateinit var service:PlaylistService
    private val api:PlayListAPI= mock()
    private val playlists=mock<List<Playlist>>()
    private val expected=Result.success(playlists)

    @Test
    fun fetchPlaylistsFromAPI()= runTest{
        service=PlaylistService(api)

        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesTpFlowResultAmdEmitsThem()= runTest{
        mockSuccessfulCase()

        assertEquals(expected, service.fetchPlaylists().first())

    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }

    @Test
    fun emitsErrorResultWhenNetworkFails()= runTest{
        mockFailCase()

        assertEquals("Something went wrong",service.fetchPlaylists().first().exceptionOrNull()?.message)

    }

    private suspend fun mockFailCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Something went wrong"))

        service = PlaylistService(api)
    }
}