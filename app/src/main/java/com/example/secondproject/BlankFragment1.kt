package com.example.secondproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.secondproject.databinding.FragmentBlank1Binding
import com.example.secondproject.dataclass.CardItem

class BlankFragment1 : Fragment() {
    private var _binding: FragmentBlank1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank1Binding.inflate(inflater, container, false)
        val view = binding.root
        binding.button.setOnClickListener {
            val inputText = binding.editTextText.text.toString()
            val card = CardItem(inputText, R.drawable.first, "first", )
            if (inputText.isEmpty()) {
                Toast.makeText(context, "Поле не може бути пустим", Toast.LENGTH_SHORT).show()
            } else if (inputText.length >= 100) {
                Toast.makeText(context, "Текст не повинен перевищувати 100 символів", Toast.LENGTH_SHORT).show()
            } else {
                val action = BlankFragment1Directions.actionBlankFragment1ToRecyclerFragment(card)  // Це метод у класі BlankNavigationDirections, який відповідає за навігацію до певного фрагмента. У цьому випадку, метод очікує один аргумент (у даному випадку, це число 224), яке буде передане як аргумент number до цільового фрагмента (тобто фрагмента, до якого виконується навігація).
//                val actionss =BlankNavigationDirections.firstNavigationAction( number=100) // Трхи гірше
                Navigation.findNavController(requireView()).navigate(action)

            }
        }
        return view
    }
}
