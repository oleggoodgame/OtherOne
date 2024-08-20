package com.example.secondproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.secondproject.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Перевірка збережених налаштувань теми при створенні фрагмента
        val isDarkMode = loadThemePreference() // Викликається функція loadThemePreference(), яка завантажує збережену тему з SharedPreferences
        //isDarkMode отримує значення true, якщо користувач раніше вибрав темний режим, або false, якщо ні.
        AppCompatDelegate.setDefaultNightMode( //  Тут перевіряється значення isDarkMode.
            // Якщо isDarkMode == true, тобто вибраний темний режим, викликається AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES), що вмикає темну тему.
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        binding.switch1.isChecked = isDarkMode

        binding.buttonHead.setOnClickListener {
            val inputText = binding.editTextTextHeader.text.toString()
            val action = SettingsFragmentDirections.actionSettingsFragmentToMainFragment(inputText)
            Navigation.findNavController(requireView()).navigate(action)
        }

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            saveThemePreference(isChecked)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    // Це рядок отримує доступ до налаштувань (SharedPreferences) з файлу "AppPreferences", який створений спеціально для збереження налаштувань додатку.
    //MODE_PRIVATE означає, що цей файл доступний тільки для цього додатку.
    private fun saveThemePreference(isDarkMode: Boolean) {
        val sharedPreferences = requireActivity().getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE)

        // Використовується редактор для змінення даних у SharedPreferences.
        // Тут починається зміна значень у цьому файлі налаштувань.

        with(sharedPreferences.edit()) {
            putBoolean("DarkMode", isDarkMode) // Зберігається логічне значення (true/false) під ключем "DarkMode".
            // Якщо isDarkMode — це true, ми зберігаємо, що темна тема активована. Якщо isDarkMode — false, то активна світла тема.
            apply() // Цей метод зберігає зміни в SharedPreferences асинхронно. Іншими словами, зміни зберігаються без затримки, але головний потік програми не блокується, що корисно для продуктивності.
        }
    }

    private fun loadThemePreference(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("AppPreferences", AppCompatActivity.MODE_PRIVATE) // Знову ж таки, отримуємо доступ до налаштувань додатку.
        return sharedPreferences.getBoolean("DarkMode", false) // false - значення за замовчуванням
//        Читаємо збережене значення під ключем "DarkMode".
//        Якщо значення було збережено раніше, повертається true або false залежно від того, яка тема була вибрана.
//        Якщо ж ніяке значення не було збережено (наприклад, при першому запуску додатку), то повертається значення за замовчуванням — у цьому випадку це false, що означає, що світла тема.
    }
}
