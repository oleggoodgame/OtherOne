package com.example.secondproject.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardItem(val text: String, val imageResId: Int, val code: String) : Parcelable
