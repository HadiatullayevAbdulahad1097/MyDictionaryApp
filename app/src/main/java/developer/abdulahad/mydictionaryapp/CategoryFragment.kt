package developer.abdulahad.mydictionaryapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulahad.mydictionaryapp.adapter.PagerCategoryAdapter
import developer.abdulahad.mydictionaryapp.database.MyDbHelper
import developer.abdulahad.mydictionaryapp.databinding.FragmentCategoryBinding
import developer.abdulahad.mydictionaryapp.databinding.ItemAddCategoryDialogBinding
import developer.abdulahad.mydictionaryapp.databinding.ItemTabBinding
import developer.abdulahad.mydictionaryapp.models.Category

class CategoryFragment : Fragment(){
    lateinit var binding: FragmentCategoryBinding
    lateinit var pagerCategoryAdapter: PagerCategoryAdapter
    lateinit var list: ArrayList<Category>
    lateinit var listIcon: ArrayList<Int>
    lateinit var listIcon2: ArrayList<Int>
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        list = myDbHelper.read()

        listIcon = ArrayList()
        listIcon2 = ArrayList()

        listIcon.add(R.drawable.category_unselected)
        listIcon.add(R.drawable.word_unselected)

        listIcon2.add(R.drawable.category)
        listIcon2.add(R.drawable.word)

        pagerCategoryAdapter = PagerCategoryAdapter(this)


        binding.apply {
        viewPager2.adapter = pagerCategoryAdapter

            back.setOnClickListener {
                findNavController().popBackStack()
                findNavController().navigate(R.id.dictinaryFragment)
            }

            tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    var custom = tab?.customView
                    custom!!.findViewById<TextView>(R.id.tv_tab).setTextColor(Color.parseColor("#FCB600"))
                    when(tab!!.position){
                        0 -> {
                            custom.findViewById<ImageView>(R.id.image_tab).setImageResource(listIcon2[0])
                            binding.add.setOnClickListener {
                                val dialog = AlertDialog.Builder(root.context).create()
                                val itemDialog = ItemAddCategoryDialogBinding.inflate(LayoutInflater.from(binding.root.context),container,false)
                                itemDialog.save.setOnClickListener {
                                    val category = itemDialog.edtCategory.text.toString()
                                    if (category.isNotEmpty()) {
                                        var mycategory = Category(
                                             category
                                        )
                                        myDbHelper.add(mycategory)
                                        list.add(mycategory)
                                    }
                                    dialog.cancel()
                                    findNavController().popBackStack()
                                    findNavController().navigate(R.id.categoryFragment)
                                }
                                itemDialog.back.setOnClickListener {
                                    dialog.cancel()
                                }
                                dialog.setView(itemDialog.root)
                                dialog.show()
                            }
                        }
                        1 -> {
                            custom.findViewById<ImageView>(R.id.image_tab)
                                .setImageResource(listIcon2[1])
                            binding.add.setOnClickListener {
                                findNavController().navigate(R.id.addWordFragment)
                            }
                        }
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

            var list = arrayOf("Categorya","So'zlar")
            TabLayoutMediator(tab,viewPager2){tab,position->
                var item = ItemTabBinding.inflate(layoutInflater)

                item.tvTab.setTextColor(Color.parseColor("#8D9BA8"))

                item.imageTab.setImageResource(listIcon[position])

                item.tvTab.text = list[position]

                tab.customView = item.root
            }.attach()
        }
        return binding.root
    }

}