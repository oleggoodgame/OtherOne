package com.example.secondproject

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.secondproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment // NavHostFragment — це спеціалізований фрагмент, який використовується для роботи з навігацією в Android додатках

    override fun onCreate(savedInstanceState: Bundle?) {
        applySavedTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        navController — це об'єкт, який відповідає за управління навігацією у вашому додатку. Він знає, який фрагмент повинен бути показаний в поточний момент, і надає функціонал для навігації між фрагментами.
//        Використовуючи navController, ви можете виконувати навігаційні дії, наприклад, перейти від одного фрагмента до іншого або керувати навігаційним стеком.

        binding.buttonNavigationView.setupWithNavController(navController)
        binding.navigationView.setupWithNavController(navController)
    }
    fun updateHeaderText(text: String) {
        val headerView = binding.navigationView.getHeaderView(0)
        val textView = headerView.findViewById<TextView>(R.id.textView2)
        textView.text = text
    }

    fun Menu(item: MenuItem) {
        // Відкриваємо NavigationView (Drawer)
        // openDrawer(binding.navigationView): цей метод відкриває бокове меню (NavigationView), яке знаходиться всередині DrawerLayout.
        binding.drawerLayout.openDrawer(binding.navigationView)
    }
    private fun applySavedTheme() { //
        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE) // Ця функція отримує доступ до файлу SharedPreferences, в якому зберігаються дані налаштувань. В даному випадку, дані зберігаються у файлі з ім'ям "AppPreferences".
        // MODE_PRIVATE: Це режим доступу до файлу. Він означає, що цей файл налаштувань може бути прочитаний і змінений лише в межах цього додатку.
        val isDarkMode = sharedPreferences.getBoolean("DarkMode", false) // sharedPreferences.getBoolean("DarkMode", false): Ми читаємо значення з налаштувань, яке зберігається під ключем "DarkMode". Якщо це значення існує, ми отримаємо його (це буде або true, або false).
        // false: Якщо значення під ключем "DarkMode" не було збережено, використовується значення за замовчуванням — у даному випадку це false, що означає "світла тема".
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) // Це команда для зміни теми додатку на темну. AppCompatDelegate — це спеціальний клас для керування темою, і MODE_NIGHT_YES включає темний режим.
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Це команда для зміни теми додатку на світлу.
        }
    }
}