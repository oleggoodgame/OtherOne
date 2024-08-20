package com.example.secondproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.secondproject.databinding.FragmentBlank2Binding
import com.example.secondproject.dataclass.CardItem

class BlankFragment2 : Fragment() {
    private var _binding: FragmentBlank2Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank2Binding.inflate(inflater, container, false)
        val view = binding.root
        val lists = listOf(R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.fourth, R.drawable.fifth)
        var index = 0
        var currentImage = lists[index]
        binding.imageViewBlank1.setImageResource(currentImage)
        binding.imageButtonBlank.setOnClickListener{
            index++
            if (index >= lists.size) {
                index = 0
            }
            currentImage = lists[index]
            binding.imageViewBlank1.setImageResource(currentImage)
        }
        binding.button.setOnClickListener{
            val inputText = binding.editTextText.text.toString()
            val card = CardItem(inputText, currentImage, "second")
            if (inputText.isEmpty()) {
                Toast.makeText(context, "Поле не може бути пустим", Toast.LENGTH_SHORT).show()
            } else if (inputText.length >= 100) {
                Toast.makeText(context, "Текст не повинен перевищувати 100 символів", Toast.LENGTH_SHORT).show()
            } else {
                val action = BlankFragment2Directions.actionBlankFragment2ToRecyclerFragment(card)  
                Navigation.findNavController(requireView()).navigate(action)

            }
        }
        return view
    }
}