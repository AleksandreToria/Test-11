package com.example.test10.data.repository

import com.example.test10.data.common.HandleResponse
import com.example.test10.data.common.Resource
import com.example.test10.data.mapper.account.toDomain
import com.example.test10.data.mapper.base.asResource
import com.example.test10.data.service.CheckAccountService
import com.example.test10.domain.model.GetAccounts
import com.example.test10.domain.repository.CheckAccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckAccountRepositoryImpl @Inject constructor(
    private val checkAccountService: CheckAccountService,
    private val handleResponse: HandleResponse
) : CheckAccountRepository {
    override suspend fun checkAccount(): Flow<Resource<GetAccounts>> {
        return handleResponse.apiCall {
            checkAccountService.checkAccount()
        }.asResource {
            it.toDomain()
        }
    }
}