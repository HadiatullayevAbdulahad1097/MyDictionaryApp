package developer.abdulahad.mydictionaryapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulahad.mydictionaryapp.adapter.PageAdapter
import developer.abdulahad.mydictionaryapp.databinding.FragmentDictinaryBinding
import developer.abdulahad.mydictionaryapp.databinding.ItemTabBinding

class DictinaryFragment : Fragment() {
    lateinit var binding: FragmentDictinaryBinding
    lateinit var list: ArrayList<String>
    lateinit var listIcon: ArrayList<Int>
    lateinit var listIcon2: ArrayList<Int>
    lateinit var pageAdapter: PageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictinaryBinding.inflate(layoutInflater)

        list = ArrayList()

        list.add("Asosiy")
        list.add("Tanlangan")

        listIcon = ArrayList()
        listIcon2 = ArrayList()

        listIcon.add(R.drawable.home_unselected)
        listIcon.add(R.drawable.like_unselected)

        listIcon2.add(R.drawable.home_selected)
        listIcon2.add(R.drawable.like_slected)

        pageAdapter = PageAdapter(this)

        binding.apply {
            add.setOnClickListener {
                findNavController().navigate(R.id.categoryFragment)
            }

            viewPager2.adapter = pageAdapter

            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    var custom = tab?.customView
                    custom!!.findViewById<TextView>(R.id.tv_tab).setTextColor(Color.parseColor("#FCB600"))
                    when(tab!!.position){
                        0 -> custom.findViewById<ImageView>(R.id.image_tab).setImageResource(listIcon2[0])
                        1 -> custom.findViewById<ImageView>(R.id.image_tab).setImageResource(listIcon2[1])
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    var custom = tab?.customView
                    custom!!.findViewById<TextView>(R.id.tv_tab).setTextColor(Color.parseColor("#8D9BA8"))
                    when(tab!!.position){
                        0 -> custom.findViewById<ImageView>(R.id.image_tab).setImageResource(listIcon[0])
                        1 -> custom.findViewById<ImageView>(R.id.image_tab).setImageResource(listIcon[1])
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            TabLayoutMediator(tab,viewPager2){tab,position ->
                var item = ItemTabBinding.inflate(layoutInflater)

                item.tvTab.setTextColor(Color.parseColor("#8D9BA8"))

                item.tvTab.text = list[position]

                item.imageTab.setImageResource(listIcon[position])

                tab.setCustomView(item.root)

            }.attach()
        }

        return binding.root
    }
}