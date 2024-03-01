package com.example.test10.presentation.screen.from_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test10.data.common.Resource
import com.example.test10.domain.usecase.GetAccountsUseCase
import com.example.test10.presentation.event.from_account.FromAccountEvent
import com.example.test10.presentation.mapper.toPresenter
import com.example.test10.presentation.state.from_account.FromAccountState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FromAccountViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase
) : ViewModel() {
    private val _fromAccountState = MutableStateFlow(FromAccountState())
    val fromAccountState: SharedFlow<FromAccountState> = _fromAccountState.asStateFlow()

    fun onEvent(event: FromAccountEvent) {
        when (event) {
            FromAccountEvent.FetchAccounts -> fetchAccounts()
            FromAccountEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun fetchAccounts() {
        viewModelScope.launch {
            getAccountsUseCase().collect() { it ->
                when (it) {
                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _fromAccountState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _fromAccountState.update { currentState ->
                            currentState.copy(
                                accounts = it.data.map {
                                    it.toPresenter()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _fromAccountState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}