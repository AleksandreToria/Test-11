package com.example.test10.presentation.callback

import com.example.test10.presentation.model.Accounts

interface AccountSelectionCallBack {
    fun onAccountSelected(account: Accounts, isFromAccount: Boolean)
}
