package com.example.test10.presentation.screen.from_account

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.R
import com.example.test10.databinding.AccountLayoutBinding
import com.example.test10.presentation.extension.loadImagesWithGlide
import com.example.test10.presentation.model.Accounts

class FromAccountRvAdapter :
    ListAdapter<Accounts, FromAccountRvAdapter.FromAccountViewHolder>(DIFF_CALLBACK) {
    var onClick: ((Accounts) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Accounts>() {
            override fun areItemsTheSame(oldItem: Accounts, newItem: Accounts) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Accounts, newItem: Accounts) =
                oldItem == newItem
        }
    }

    enum class CardType {
        VISA,
        MASTER_CARD
    }

    inner class FromAccountViewHolder(private val binding: AccountLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind() = with(binding) {
            val account = getItem(adapterPosition)

            when (account.cardType) {
                CardType.VISA.toString() -> imageIv.loadImagesWithGlide(R.drawable.ic_visa)
                CardType.MASTER_CARD.toString() -> imageIv.loadImagesWithGlide(R.drawable.ic_mastercard)
            }

            accountNameTv.text = account.accountName
            cardTypeTv.text = account.cardType
            currencyTypeTv.text = account.currencyType
            balanceTv.text = account.balance.toString()
            accountNumberTv.text = "**${account.accountNumber.takeLast(4)}"
        }

        fun clickAccount() {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick?.invoke(currentList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FromAccountViewHolder {
        return FromAccountViewHolder(
            AccountLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FromAccountViewHolder, position: Int) {
        holder.bind()
        holder.clickAccount()
    }
}