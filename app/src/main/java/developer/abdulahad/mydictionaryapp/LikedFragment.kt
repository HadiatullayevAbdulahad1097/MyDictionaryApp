package developer.abdulahad.mydictionaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.adapter.RvAdapter
import developer.abdulahad.mydictionaryapp.databinding.FragmentLikedBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary

class LikedFragment : Fragment() {
    lateinit var binding: FragmentLikedBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<MyRoom>
    lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikedBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        list = ArrayList()

        for (i in appDatabase.dao().read()){
            if (i.like!!) list.add(i)
        }

        rvAdapter = RvAdapter(list,object : RvAdapter.Click{
            override fun clickItem(myRoom: MyRoom, position: Int) {
                MyDictionary.position = position
                findNavController().navigate(R.id.informationFragment)
            }
        })

        binding.rv.adapter = rvAdapter

        return binding.root
    }
}