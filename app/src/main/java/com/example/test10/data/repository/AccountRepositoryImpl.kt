package com.example.test10.data.repository

import com.example.test10.data.common.HandleResponse
import com.example.test10.data.common.Resource
import com.example.test10.data.mapper.account.toDomain
import com.example.test10.data.mapper.base.asResource
import com.example.test10.data.service.AccountService
import com.example.test10.domain.model.GetAccounts
import com.example.test10.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
    private val handleResponse: HandleResponse
) : AccountRepository {
    override suspend fun getAccounts(): Flow<Resource<List<GetAccounts>>> {
        return handleResponse.apiCall {
            accountService.getAccounts()
        }.asResource { it ->
            it.map {
                it.toDomain()
            }
        }
    }
}