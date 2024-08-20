package com.example.secondproject
import com.example.secondproject.dataclass.CardItem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.secondproject.databinding.CardItemBinding

class RecyclerViewAdapter(private val onItemClick: (CardItem, Int) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder>() {

    private val items: MutableList<CardItem> = mutableListOf()
    // 2
    class RecyclerHolder(item: View, val onItemClick: (CardItem, Int) -> Unit) : RecyclerView.ViewHolder(item) {
        val binding = CardItemBinding.bind(item)

        fun bind(cardItem: CardItem, position: Int) = with(binding) {
            textViewAdapter.text = cardItem.text
            imageViewAdapter.setImageResource(cardItem.imageResId)
            itemView.setOnClickListener {
                onItemClick(cardItem, position) // иклик переданої функції, яка визначена в конструкторі адаптера (RecyclerViewAdapter) і містить логіку для обробки натискання на елемент. Тут немає рекурсії, оскільки метод onItemClick не викликає сам себе, а просто передає потрібні дані (елемент і позицію) в колбек.
            }
        }
    }
    // 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return RecyclerHolder(view, onItemClick)
    }
    //3
    override fun getItemCount(): Int {
        return items.size
    }
    // 4
    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.bind(items[position], position)
    }

    fun addItem(item: CardItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItems(newItems: List<CardItem>) {
        val startPos = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPos, newItems.size)
    }
}

