package com.example.secondproject
import com.example.secondproject.dataclass.CardItem

import androidx.lifecycle.ViewModel
// ViewModel — це компонент архітектури Android, який відповідає за збереження та управління даними, що стосуються UI (користувацького інтерфейсу), в життєвому циклі. Основна ідея ViewModel полягає в тому, щоб забезпечити стійкість даних, навіть якщо відбудеться зміна конфігурації (наприклад, поворот екрана).
class RecyclerViewModel : ViewModel() {
    val items: MutableList<CardItem> = mutableListOf()

    fun addItem(item: CardItem) {
        items.add(item)
    }

    fun all(): Int{
        return items.size
    }
}
