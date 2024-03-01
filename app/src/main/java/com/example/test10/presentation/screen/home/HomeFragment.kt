package com.example.test10.presentation.screen.home

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import com.example.test10.R
import com.example.test10.databinding.FragmentHomeBinding
import com.example.test10.presentation.base.BaseFragment
import com.example.test10.presentation.callback.AccountSelectionCallBack
import com.example.test10.presentation.extension.loadImagesWithGlide
import com.example.test10.presentation.model.Accounts
import com.example.test10.presentation.screen.from_account.FromAccountBottomSheet
import com.example.test10.presentation.screen.to_account.ToAccountBottomSheet
import com.example.test10.presentation.state.home.HomeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    AccountSelectionCallBack {

    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun bind() {

    }

    override fun bindViewActionListeners() {
        binding.apply {
            fromAccountBtn.setOnClickListener {
                showFromAccountBottomSheet()
            }

            toAccountBtn.setOnClickListener {
                showToAccountBottomSheet()
            }
        }
    }

    override fun bindObserves() {
    }

    private fun handleHomeState(state: HomeState) {

    }

    private fun showFromAccountBottomSheet() {
        val bottomSheet = FromAccountBottomSheet().apply {
            accountSelectionListener = object : AccountSelectionCallBack {
                override fun onAccountSelected(account: Accounts, isFromAccount: Boolean) {
                    updateUIWithSelectedAccount(account, isFromAccount = true)
                }
            }
        }
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }

    private fun showToAccountBottomSheet() {
        val bottomSheet = ToAccountBottomSheet().apply {
            accountDetailsFetchedListener = object : AccountSelectionCallBack {
                override fun onAccountSelected(account: Accounts, isFromAccount: Boolean) {
                    updateUIWithSelectedAccount(account, isFromAccount = false)
                }
            }
        }
        bottomSheet.show(parentFragmentManager, bottomSheet.tag)
    }

    override fun onAccountSelected(account: Accounts, isFromAccount: Boolean) {
        updateUIWithSelectedAccount(account, isFromAccount)
    }


    @SuppressLint("SetTextI18n")
    private fun updateUIWithSelectedAccount(account: Accounts, isFromAccount: Boolean) {
        if (isFromAccount) {
            binding.balanceTv.text = "${account.balance} ${account.currencyType}"
            binding.cardNumberTv.text = "**${account.accountNumber.takeLast(4)}"
            val cardImageResId = when (account.cardType) {
                "VISA" -> R.drawable.ic_visa
                "MASTER_CARD" -> R.drawable.ic_mastercard
                else -> R.drawable.ic_launcher_background
            }
            binding.coverIv.loadImagesWithGlide(cardImageResId)
        } else {
            binding.balanceTv2.text = "${account.balance} ${account.currencyType}"
            binding.cardNumberTv2.text = "**${account.accountNumber.takeLast(4)}"
            val cardImageResId2 = when (account.cardType) {
                "VISA" -> R.drawable.ic_visa
                "MASTER_CARD" -> R.drawable.ic_mastercard
                else -> R.drawable.ic_launcher_background
            }
            binding.coverIv2.loadImagesWithGlide(cardImageResId2)
        }
    }

}