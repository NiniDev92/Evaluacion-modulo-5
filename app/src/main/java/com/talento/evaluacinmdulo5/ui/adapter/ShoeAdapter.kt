package com.talento.evaluacinmdulo5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.talento.evaluacinmdulo5.R
import com.talento.evaluacinmdulo5.databinding.ItemShoeBinding
import com.talento.evaluacinmdulo5.model.ShoeItem
import java.text.NumberFormat
import java.util.Locale

class ShoeAdapter(
    private val onItemClick: (ShoeItem) -> Unit
) : RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {

    private val items = mutableListOf<ShoeItem>()
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))

    fun submitList(newItems: List<ShoeItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ItemShoeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ShoeViewHolder(
        private val binding: ItemShoeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoeItem) {
            binding.textName.text = item.nombre
            binding.textPrice.text = currencyFormatter.format(item.precio)
            Glide.with(binding.imageShoe)
                .load(item.urlImagen)
                .placeholder(R.drawable.bg_shoe_placeholder)
                .error(R.drawable.bg_shoe_placeholder)
                .centerCrop()
                .into(binding.imageShoe)

            binding.root.setOnClickListener { onItemClick(item) }
        }
    }
}
