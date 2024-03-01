package com.example.test10.presentation.event.from_account

sealed class FromAccountEvent {
    data object FetchAccounts : FromAccountEvent()
    data object ResetErrorMessage : FromAccountEvent()
}