package com.example.test10.domain.repository

import com.example.test10.data.common.Resource
import com.example.test10.domain.model.GetAccounts
import kotlinx.coroutines.flow.Flow

interface CheckAccountRepository {
    suspend fun checkAccount(): Flow<Resource<GetAccounts>>
}