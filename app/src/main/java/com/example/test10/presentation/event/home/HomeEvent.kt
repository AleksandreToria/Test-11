package com.example.test10.presentation.event.home

sealed class HomeEvent {
    data object FetchAccounts : HomeEvent()
    data object ResetErrorMessage : HomeEvent()
}