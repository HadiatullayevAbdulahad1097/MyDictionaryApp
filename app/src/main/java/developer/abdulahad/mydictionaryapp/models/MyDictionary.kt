package developer.abdulahad.mydictionaryapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import developer.abdulahad.mydictionary.Room.Entity.MyRoom

object MyDictionary {
    var position = 0
    var myRoom = MyRoom()
    var tabPosition = 0
    var text = ""
    var list2 = ArrayList<MyRoom>()
}