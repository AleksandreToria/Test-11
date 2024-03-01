package com.example.test10.presentation.state.from_account

import com.example.test10.presentation.model.Accounts

data class FromAccountState(
    val isLoading: Boolean = false,
    val accounts: List<Accounts>? = null,
    val errorMessage: String? = null
)