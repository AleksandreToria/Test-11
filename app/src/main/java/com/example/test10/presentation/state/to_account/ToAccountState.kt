package com.example.test10.presentation.state.to_account

import com.example.test10.presentation.model.Accounts

data class ToAccountState(
    val isLoading: Boolean = false,
    val account: Accounts? = null,
    val errorMessage: String? = null
)
