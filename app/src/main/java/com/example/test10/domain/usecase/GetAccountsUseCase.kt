package com.example.test10.domain.usecase

import com.example.test10.domain.repository.AccountRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() = accountRepository.getAccounts()
}