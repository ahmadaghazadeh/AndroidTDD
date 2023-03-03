package com.arad.androidtdd.playlist

import com.arad.androidtdd.utils.BaseUnitTest
import com.arad.androidtdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.times

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistViewModelShould:BaseUnitTest() {


    private val repository: PlaylistRepository =mock()
    private val playlists=mock<List<Playlist>>()
    private val expected=Result.success(playlists)
    private val exception=java.lang.RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromRepository() = runTest{

        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository,times(1)). getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository(){
        val viewModel = mockSuccessfulCase()
        assertEquals(expected,viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        runTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }
        val viewModel=PlaylistViewModel(repository)
        assertEquals(exception,viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runTest {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }
}