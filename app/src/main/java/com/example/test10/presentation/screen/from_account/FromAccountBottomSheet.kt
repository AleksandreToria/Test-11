package com.example.test10.presentation.screen.from_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test10.databinding.FragmentFromAccountBottomSheetBinding
import com.example.test10.presentation.callback.AccountSelectionCallBack
import com.example.test10.presentation.event.from_account.FromAccountEvent
import com.example.test10.presentation.extension.showSnackBar
import com.example.test10.presentation.state.from_account.FromAccountState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FromAccountBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentFromAccountBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FromAccountViewModel by viewModels()
    private lateinit var adapter: FromAccountRvAdapter

    var accountSelectionListener: AccountSelectionCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFromAccountBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onEvent(FromAccountEvent.FetchAccounts)

        fetchAccounts()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = FromAccountRvAdapter().apply {
            onClick = { account ->
                accountSelectionListener?.onAccountSelected(account, true)
                dismiss()
            }
        }
        binding.accountRv.adapter = adapter
    }


    private fun fetchAccounts() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fromAccountState.collect {
                    handleFromAccountHomeState(it)
                }
            }
        }
    }

    private fun handleFromAccountHomeState(state: FromAccountState) = binding.apply {
        binding.progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.accounts?.let {
            adapter.submitList(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            viewModel.onEvent(FromAccountEvent.ResetErrorMessage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}