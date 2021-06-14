package com.tmobile.subbu.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmobile.subbu.databinding.ItemCardBinding
import com.tmobile.subbu.model.data.Card

/**
 * Adapter which displays the response data in the list format
 */
class CardsAdapter :
    ListAdapter<Card, CardsAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cardType == newItem.cardType
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cardInfo.value == newItem.cardInfo.value ||
                    oldItem.cardInfo.title == newItem.cardInfo.title ||
                    oldItem.cardInfo.image == newItem.cardInfo.image ||
                    oldItem.cardInfo.description == newItem.cardInfo.description
        }
    }) {

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            binding.cardInfo = card.cardInfo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}