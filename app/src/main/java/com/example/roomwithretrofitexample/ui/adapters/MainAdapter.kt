package com.example.roomwithretrofitexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomwithretrofitexample.R
import com.example.roomwithretrofitexample.databinding.ItemLyBinding
import com.example.roomwithretrofitexample.models.CryptoModel

class MainAdapter : ListAdapter<CryptoModel, MainAdapter.ItemHolder>(CryptoDiffUtil()) {

    companion object {
        const val IMAGE_CRYPTO_URL = "https://coinicons-api.vercel.app/api/icon/"
    }

    inner class ItemHolder(private val b: ItemLyBinding) : RecyclerView.ViewHolder(b.root) {

        fun bind(result: CryptoModel) {
            b.crName.text = result.name
            b.crPrice.text = result.price_usd + " $"

            val cryIcons: String = result.symbol.lowercase()
            Glide.with(itemView.context).load(IMAGE_CRYPTO_URL + cryIcons).error(R.color.black)
                .placeholder(R.drawable.ic_launcher_background)
                .into(b.crImage)
        }

    }

    internal class CryptoDiffUtil : DiffUtil.ItemCallback<CryptoModel>() {
        override fun areItemsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
            return newItem.name == oldItem.name
        }

        override fun areContentsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemLyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val itemData = getItem(position)

        holder.bind(itemData)
    }
}
