package com.arad.androidtdd.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule


abstract class BaseUnitTest  {
    @get:Rule
    var coroutinesTestRule=MainCoroutineScopeRule() // allow test coroutine

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule() // allow test livedata
}