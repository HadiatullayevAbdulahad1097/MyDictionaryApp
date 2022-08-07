package developer.abdulahad.mydictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.adapter.RvAdapter
import developer.abdulahad.mydictionaryapp.databinding.FragmentItemPagerInsideBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary

class ItemPagerInsideFragment : Fragment() {
    lateinit var binding: FragmentItemPagerInsideBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var appDatabase: AppDatabase
    lateinit var lifecycleOwner: LifecycleOwner
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemPagerInsideBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        lifecycleOwner = LifecycleOwner { lifecycle }


        rvAdapter = RvAdapter(MyDictionary.list2,object : RvAdapter.Click{
            override fun clickItem(myRoom: MyRoom, position: Int) {
                MyDictionary.position = position
                findNavController().navigate(R.id.informationFragment)
            }
        })

        binding.rv.adapter = rvAdapter

        return binding.root
    }
}