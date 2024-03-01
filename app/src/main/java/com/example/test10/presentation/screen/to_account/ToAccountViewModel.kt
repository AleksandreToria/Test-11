package com.example.test10.presentation.screen.to_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test10.data.common.Resource
import com.example.test10.domain.usecase.GetCheckAccountUseCase
import com.example.test10.presentation.event.to_account.ToAccountEvent
import com.example.test10.presentation.mapper.toPresenter
import com.example.test10.presentation.state.to_account.ToAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToAccountViewModel @Inject constructor(
    private val getCheckAccountUseCase: GetCheckAccountUseCase
) : ViewModel() {
    private val _itAccountState = MutableStateFlow(ToAccountState())
    val toAccountState: SharedFlow<ToAccountState> = _itAccountState.asStateFlow()

    fun onEvent(event: ToAccountEvent) {
        when (event) {
            is ToAccountEvent.CheckAccount -> checkAccount()
            ToAccountEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun checkAccount() {
        viewModelScope.launch {
            getCheckAccountUseCase().collect() { it ->
                when (it) {
                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _itAccountState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _itAccountState.update { currentState ->
                            currentState.copy(
                                account = it.data.toPresenter()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _itAccountState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}