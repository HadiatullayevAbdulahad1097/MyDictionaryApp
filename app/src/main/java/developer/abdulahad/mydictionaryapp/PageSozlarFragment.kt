package developer.abdulahad.mydictionaryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.adapter.RvWordAdapter
import developer.abdulahad.mydictionaryapp.database.MyDbHelper
import developer.abdulahad.mydictionaryapp.databinding.FragmentPageSozlarBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary

class PageSozlarFragment : Fragment() {
    lateinit var binding: FragmentPageSozlarBinding
    lateinit var rvWordAdapter: RvWordAdapter
    lateinit var list: ArrayList<MyRoom>
    lateinit var appDatabase: AppDatabase
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPageSozlarBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        myDbHelper = MyDbHelper(binding.root.context)

        list = appDatabase.dao().read() as ArrayList

        rvWordAdapter = RvWordAdapter(list, object : RvWordAdapter.RvActionWord {
            override fun showPopup(view: View, myRoom: MyRoom, position: Int) {
                var popupMenu = PopupMenu(binding.root.context, view)
                popupMenu.inflate(R.menu.my_menu)

                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.edit -> {
                            MyDictionary.myRoom = myRoom
                            findNavController().navigate(R.id.editFragment)
                        }
                        R.id.delete -> {
                            appDatabase.dao().delete(myRoom)
                            list.remove(myRoom)
                            rvWordAdapter.notifyItemRemoved(position)
                            Toast.makeText(binding.root.context, "Delete", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    true
                }
                popupMenu.show()
            }
        })

        binding.rv.adapter = rvWordAdapter

        return binding.root
    }
}