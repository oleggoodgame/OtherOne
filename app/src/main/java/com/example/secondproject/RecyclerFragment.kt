package com.example.secondproject

import android.content.Intent
import com.example.secondproject.dataclass.CardItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.secondproject.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecyclerViewModel by activityViewModels()
    // by: Ключове слово by використовується для делегування. Воно дозволяє використовувати певну властивість або метод з іншого класу або об'єкта. У цьому випадку властивість viewModel делегується об'єкту, що надає ViewModel.
    // activityViewModels(): Це спеціальна функція, яка надає вам ViewModel, пов’язану з активністю, до якої належить ваш фрагмент. Це означає, що всі фрагменти, які використовують цей метод, будуть спільно використовувати одну й ту ж інстанцію ViewModel. Таким чином, ви можете ділитися даними між різними фрагментами однієї активності.
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = RecyclerViewAdapter { cardItem, position ->
            val intent = Intent(requireContext(), OtherMainActivity::class.java).apply {
                putExtra("name", cardItem.text)
                putExtra("photo", cardItem.imageResId)
                putExtra("position", position)
            }
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapter

        // Додаємо дані до адаптера з ViewModel
        adapter.addItems(viewModel.items)

        try {
            val args = RecyclerFragmentArgs.fromBundle(requireArguments())
            val cardItemGet = args.Dataclass
            if (cardItemGet.code == "first") {
                findNavController().popBackStack(R.id.blankFragment1, true)
            }
            else if(cardItemGet.code=="second"){
                findNavController().popBackStack(R.id.blankFragment2, true)
            }
            viewModel.addItem(cardItemGet)
            adapter.addItem(cardItemGet)
        } catch (ex: Exception) {
            // Обробка винятків
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
