package com.example.secondproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.secondproject.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    // У Fragment використовується nullable змінна _binding, оскільки фрагменти можуть створювати та знищувати свої представлення багато разів протягом їхнього життєвого циклу. Ця змінна є приватною, щоб обмежити її доступ і переконатися, що до неї можна звертатися лише через binding.
    private val binding get() = _binding!!
    //  Це властивість, яка дозволяє зручно отримувати доступ до _binding. Використання !! (force unwrap) гарантує, що ми отримуємо доступ до non-null версії binding, але при цьому потрібно бути обережним, щоб не викликати NullPointerException, коли binding недоступний. Ця властивість дозволяє зручно використовувати binding як non-null змінну в межах фрагмента.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        // або             Navigation.findNavController(view).navigate(R.id.action_addFragment_to_blankFragment2)
        binding.button.setOnClickListener {
            findNavController(view).navigate(R.id.action_addFragment_to_blankFragment2)
        }

        // Навігація для другої кнопки (Image)
        binding.button1.setOnClickListener {
            findNavController(view).navigate(R.id.action_addFragment_to_blankFragment1)
        }

        return view
    }
    // Важливо очистити _binding у onDestroyView(), оскільки представлення фрагмента може бути знищене, коли фрагмент стає невидимим. Очищення _binding допомагає уникнути утечок пам'яті, коли Fragment знищує свій інтерфейс, але залишається в пам'яті.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
