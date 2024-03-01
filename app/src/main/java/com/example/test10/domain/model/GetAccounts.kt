package com.example.test10.domain.model

data class GetAccounts(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val currencyType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String
)
