package com.example.test10.domain.usecase

import com.example.test10.domain.repository.CheckAccountRepository
import javax.inject.Inject

class GetCheckAccountUseCase @Inject constructor(
    private val checkAccountRepository: CheckAccountRepository
) {
    suspend operator fun invoke() = checkAccountRepository.checkAccount()
}