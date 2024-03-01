package com.example.test10.data.mapper.account

import com.example.test10.data.model.AccountDto
import com.example.test10.domain.model.GetAccounts

fun AccountDto.toDomain(): GetAccounts =
    GetAccounts(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        currencyType = currencyType,
        cardType = cardType,
        balance = balance ?: 0,
        cardLogo = cardLogo ?: ""
    )