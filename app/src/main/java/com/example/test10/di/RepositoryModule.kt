package com.example.test10.di

import com.example.test10.data.common.HandleResponse
import com.example.test10.data.repository.AccountRepositoryImpl
import com.example.test10.data.repository.CheckAccountRepositoryImpl
import com.example.test10.data.service.AccountService
import com.example.test10.data.service.CheckAccountService
import com.example.test10.domain.repository.AccountRepository
import com.example.test10.domain.repository.CheckAccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideAccountRepository(
        accountService: AccountService,
        handleResponse: HandleResponse
    ): AccountRepository {
        return AccountRepositoryImpl(
            accountService = accountService,
            handleResponse = handleResponse
        )
    }

    @Singleton
    @Provides
    fun provideCheckAccountRepository(
        checkAccountService: CheckAccountService,
        handleResponse: HandleResponse
    ): CheckAccountRepository {
        return CheckAccountRepositoryImpl(
            checkAccountService = checkAccountService,
            handleResponse = handleResponse
        )
    }
}