package developer.abdulahad.mydictionaryapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionary.Room.AppDatabase
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.database.MyDbHelper
import developer.abdulahad.mydictionaryapp.databinding.FragmentAddWordBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddWordFragment : Fragment() {
    lateinit var binding: FragmentAddWordBinding
    lateinit var appDatabase: AppDatabase
    lateinit var myDbHelper: MyDbHelper
    lateinit var list: ArrayList<String>
    lateinit var list2: ArrayList<MyRoom>
    var absolutePath = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWordBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        myDbHelper = MyDbHelper(binding.root.context)

        list = ArrayList()

        list2 = appDatabase.dao().read() as ArrayList

        for (i in myDbHelper.read()) {
            list.add(i.category)
        }

        binding.apply {

            imageAdd.setOnClickListener {
                getImageContent.launch("image/*")
            }

            back.setOnClickListener {
                findNavController().popBackStack()
            }

            spinnerKategory.adapter =
                ArrayAdapter(binding.root.context, android.R.layout.simple_list_item_1, list)

            save.setOnClickListener {
                val dictionary = edtDictionary.text.toString()
                val translate = edtTranslate.text.toString()
                val spinner = list[spinnerKategory.selectedItemPosition]
                MyDictionary.position = spinnerKategory.selectedItemPosition
                val image = absolutePath
                if (dictionary.isNotEmpty() && translate.isNotEmpty() && image.isNotBlank()){
                    var myRoom = MyRoom(
                        dictionary,
                        translate,
                        spinner,
                        false,
                        image
                    )
                    appDatabase.dao().create(myRoom)
                    list2.add(myRoom)
                    findNavController().popBackStack()
                }else{
                    Toast.makeText(binding.root.context, "Empty", Toast.LENGTH_SHORT).show()
                }
            }
            hinter.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

@SuppressLint("SimpleDateFormat")
private val getImageContent =
    registerForActivityResult(ActivityResultContracts.GetContent()) {
        it ?: return@registerForActivityResult
        binding.imageAdd.setImageURI(it)
        val inputStream = requireActivity().contentResolver.openInputStream(it)
        val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val file = File(requireActivity().filesDir, "$title.jpg")
        val fileOutputStream = FileOutputStream(file)
        inputStream?.copyTo(fileOutputStream)
        inputStream?.close()
        fileOutputStream.close()
        absolutePath = file.absolutePath
        Toast.makeText(context, absolutePath, Toast.LENGTH_SHORT).show()
    }
}