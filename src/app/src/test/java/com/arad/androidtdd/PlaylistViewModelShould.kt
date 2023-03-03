package com.arad.androidtdd

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arad.androidtdd.utils.MainCoroutineScopeRule
import com.arad.androidtdd.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.times

@OptIn(ExperimentalCoroutinesApi::class)
class PlaylistViewModelShould {


    @get:Rule
    var coroutinesTestRule=MainCoroutineScopeRule() // allow test coroutine

    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule() // allow test livedata

    private val repository:PlaylistRepository=mock()
    private val playlists=mock<List<Playlist>>()
    private val expected=Result.success(playlists)

    init {

    }

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