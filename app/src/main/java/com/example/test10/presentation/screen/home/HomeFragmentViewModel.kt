package com.example.test10.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.test10.presentation.event.home.HomeEvent
import com.example.test10.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ResetErrorMessage -> updateErrorMessage(message = null)
            else -> {}
        }
    }

    private fun updateErrorMessage(message: String?) {
        _homeState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}
