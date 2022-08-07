package developer.abdulahad.mydictionaryapp

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionaryapp.databinding.FragmentInformationBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary

class InformationFragment : Fragment() {
    lateinit var binding: FragmentInformationBinding
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInformationBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        binding.malumot.text = MyDictionary.list2[MyDictionary.position].uzbek
        binding.dicTitle.text = MyDictionary.list2[MyDictionary.position].english
        binding.dicImage.setImageURI(Uri.parse(MyDictionary.list2[MyDictionary.position].image))

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        if (MyDictionary.list2[MyDictionary.position].like!!){
            binding.like.setImageResource(R.drawable.ic_selected)
        }else{
            binding.like.setImageResource(R.drawable.ic_unselested)
        }

        binding.like.setOnClickListener {
            if (MyDictionary.list2[MyDictionary.position].like!!){
                binding.like.setImageResource(R.drawable.ic_unselested)
                MyDictionary.list2[MyDictionary.position].like = false
            }else{
                binding.like.setImageResource(R.drawable.ic_selected)
                MyDictionary.list2[MyDictionary.position].like = true
            }
            appDatabase.dao().update(MyDictionary.list2[MyDictionary.position])
        }

        return binding.root
    }
}