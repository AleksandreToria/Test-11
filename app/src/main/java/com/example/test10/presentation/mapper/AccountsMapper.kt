package com.example.test10.presentation.mapper

import com.example.test10.domain.model.GetAccounts
import com.example.test10.presentation.model.Accounts

fun GetAccounts.toPresenter(): Accounts =
    Accounts(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        currencyType = currencyType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )