package developer.abdulahad.mydictionaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionaryapp.adapter.RvCategoryAdapter
import developer.abdulahad.mydictionaryapp.database.MyDbHelper
import developer.abdulahad.mydictionaryapp.databinding.FragmentPageKategoryBinding
import developer.abdulahad.mydictionaryapp.databinding.ItemAddCategoryDialogBinding
import developer.abdulahad.mydictionaryapp.models.Category

class PageKategoryFragment : Fragment() {
    lateinit var binding: FragmentPageKategoryBinding
    lateinit var rvCategoryAdapter: RvCategoryAdapter
    lateinit var list: ArrayList<Category>
    lateinit var myDbHelper: MyDbHelper
    lateinit var appDatabase: AppDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageKategoryBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        myDbHelper = MyDbHelper(binding.root.context)

        list = myDbHelper.read()

        rvCategoryAdapter = RvCategoryAdapter(list, object : RvCategoryAdapter.RvAction {
            override fun showPopup(view: View, category: Category) {
                var popupMenu = PopupMenu(binding.root.context, view)
                popupMenu.inflate(R.menu.my_menu)

                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit -> {
                            val alertDialog = AlertDialog.Builder(binding.root.context).create()
                            val item = ItemAddCategoryDialogBinding.inflate(layoutInflater)

                            item.edtCategory.setText(category.category)
                            val index = list.indexOf(category)
                            item.save.setOnClickListener {
                                category.category = item.edtCategory.text.toString()
                                list[index]
                                rvCategoryAdapter.notifyItemChanged(index)
                                myDbHelper.update(category)
                                Toast.makeText(binding.root.context, "Edit", Toast.LENGTH_SHORT).show()
                                alertDialog.cancel()
                            }
                            item.back.setOnClickListener {
                                alertDialog.cancel()
                            }
                            alertDialog.setView(item.root)
                            alertDialog.show()
                        }
                        R.id.delete -> {
                            for (i in appDatabase.dao().read()){
                                if (i.category == category.category){
                                    appDatabase.dao().delete(i)
                                }
                            }
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.categoryFragment)
                            myDbHelper.delete(category)
                            list.remove(category)
                            val index = list.indexOf(category)
                            rvCategoryAdapter.notifyItemRemoved(index)
                            Toast.makeText(binding.root.context, "Delete", Toast.LENGTH_SHORT).show()
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        })

        binding.rv.adapter = rvCategoryAdapter

        return binding.root
    }
}