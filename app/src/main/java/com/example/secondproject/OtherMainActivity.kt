package com.example.secondproject

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.secondproject.databinding.ActivityMainBinding

class OtherMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_item_intent)

        val detailPhoto: ImageView = findViewById(R.id.imageView3)
        val detailText: TextView = findViewById(R.id.textView5)

        val name = intent.getStringExtra("name")
        val photo = intent.getIntExtra("photo", 0)
        val position = intent.getIntExtra("position", -1)
        detailText.text = name
        detailPhoto.setImageResource(photo)

        // Додаємо обробник для видалення елемента, якщо потрібно
        val deleteButton: Button = findViewById(R.id.delete)
        deleteButton.setOnClickListener {

        }
    }
}
