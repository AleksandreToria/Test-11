package com.example.test10.presentation.model

data class Accounts(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val currencyType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String
)