package com.example.test10.data.service

import com.example.test10.data.model.AccountDto
import retrofit2.Response
import retrofit2.http.GET

interface CheckAccountService {
    @GET("4253786f-3500-4148-9ebc-1fe7fb9dc8d5?account_number=EU67JG7744036080300903")
    suspend fun checkAccount(): Response<AccountDto>
}