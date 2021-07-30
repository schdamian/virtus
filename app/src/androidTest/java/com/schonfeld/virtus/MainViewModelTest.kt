package com.schonfeld.virtus

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.schonfeld.virtus.models.DataState
import com.schonfeld.virtus.repository.DataProvider
import com.schonfeld.virtus.ui.viewModels.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel
    private val observerState: Observer<DataState> = mockk(relaxed = true)
    private val repo = mockk<DataProvider>()

    @Before
    fun before() {
        viewModel = MainViewModel(repo)
        viewModel.state.observeForever(observerState)
    }

    @Test
    fun stateIsEmittingSuccess() {
        coEvery { repo.requestHits() } returns mockk()

        viewModel.getHits()

        io.mockk.verify {
            observerState.onChanged(DataState(isLoading = true))
            observerState.onChanged(DataState(isSuccess = true))
        }
    }

    @Test
    fun stateIsEmittingError() {
        coEvery { repo.requestHits() } throws Exception()

        viewModel.getHits()

        io.mockk.verify {
            observerState.onChanged(DataState(isLoading = true))
            observerState.onChanged(DataState(isError = true))
        }
    }
}