package developer.abdulahad.mydictionaryapp

import android.annotation.SuppressLint
import android.net.Uri
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
import developer.abdulahad.mydictionaryapp.databinding.FragmentEditBinding
import developer.abdulahad.mydictionaryapp.models.MyDictionary
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class EditFragment : Fragment() {
    lateinit var binding: FragmentEditBinding
    lateinit var list:ArrayList<String>
    lateinit var appDatabase: AppDatabase
    var absolutePath = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater)

        appDatabase = AppDatabase.getInstance(binding.root.context)

        list = ArrayList()

        for (i in appDatabase.dao().read()){
            list.add(i.category!!)
        }

        binding.apply {
            imageAdd.setOnClickListener {
                getImageContent.launch("image/*")
            }
            spinnerKategory.adapter = ArrayAdapter(binding.root.context,android.R.layout.simple_list_item_1,list)
            edtDictionary.setText(MyDictionary.myRoom.english)
            edtTranslate.setText(MyDictionary.myRoom.uzbek)
            imageAdd.setImageURI(Uri.parse(MyDictionary.myRoom.image))
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
                    appDatabase.dao().update(myRoom)
                    Toast.makeText(binding.root.context, "Edit", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                back.setOnClickListener {
                    findNavController().popBackStack()
                }
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