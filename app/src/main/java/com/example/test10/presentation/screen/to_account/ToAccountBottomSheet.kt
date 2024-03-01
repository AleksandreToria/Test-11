package com.example.test10.presentation.screen.to_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test10.databinding.FragmentToAccountBottomSheetBinding
import com.example.test10.presentation.callback.AccountSelectionCallBack
import com.example.test10.presentation.event.to_account.ToAccountEvent
import com.example.test10.presentation.state.to_account.ToAccountState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToAccountBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentToAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToAccountViewModel by viewModels()
    var accountDetailsFetchedListener: AccountSelectionCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSubmitButton()
        bindToFlow()
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val inputId = binding.etId.text.toString()
            val inputAccountNumber = binding.etAccountNumber.text.toString()
            viewModel.onEvent(
                ToAccountEvent.CheckAccount(
                    inputId,
                    inputAccountNumber,
                    number = "0"
                )
            )
        }
    }

    private fun bindToFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.toAccountState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: ToAccountState) {
        if (state.isLoading) {
            binding.progress.visibility = View.VISIBLE
        } else {
            binding.progress.visibility = View.GONE
        }

        state.errorMessage?.let { errorMessage ->
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
            viewModel.onEvent(ToAccountEvent.ResetErrorMessage)
        }

        state.account?.let { account ->
            accountDetailsFetchedListener?.onAccountSelected(account, false)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
