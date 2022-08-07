package developer.abdulahad.mydictionaryapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.adapter.PageInsideAdapter
import developer.abdulahad.mydictionaryapp.database.MyDbHelper
import developer.abdulahad.mydictionaryapp.databinding.FragmentInsideDictionaryBinding
import developer.abdulahad.mydictionaryapp.models.Category
import developer.abdulahad.mydictionaryapp.models.MyDictionary

class InsideDictionaryFragment : Fragment() {
    lateinit var binding: FragmentInsideDictionaryBinding
    lateinit var list2: ArrayList<Category>
    lateinit var pageInsideAdapter: PageInsideAdapter
    lateinit var myDbHelper: MyDbHelper
    lateinit var appDatabase: AppDatabase
    lateinit var listadapter:ArrayList<MyRoom>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsideDictionaryBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        myDbHelper = MyDbHelper(binding.root.context)

        listadapter = ArrayList()

        list2 = myDbHelper.read()


        binding.apply {

            listener(0)
            pageInsideAdapter = PageInsideAdapter(list2,this@InsideDictionaryFragment)
            viewPager2.adapter = pageInsideAdapter

            TabLayoutMediator(tab,viewPager2){tab,position->

                tab.text = list2[position].category

                MyDictionary.tabPosition = tab.position

            }.attach()

            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    listener(tab!!.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

        }
        return binding.root
    }
    private fun listener(position: Int) {
        if (MyDictionary.list2.isNotEmpty()) MyDictionary.list2.clear()
        for (i in appDatabase.dao().read()) {
            if (list2[position].category == i.category) MyDictionary.list2.add(i)
        }
    }
}