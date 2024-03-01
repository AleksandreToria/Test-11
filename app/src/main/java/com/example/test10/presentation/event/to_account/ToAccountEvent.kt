package com.example.test10.presentation.event.to_account

sealed class ToAccountEvent {
    data class CheckAccount(val cardNumber: String, val id: String, val number: String) :
        ToAccountEvent()

    data object ResetErrorMessage : ToAccountEvent()
}