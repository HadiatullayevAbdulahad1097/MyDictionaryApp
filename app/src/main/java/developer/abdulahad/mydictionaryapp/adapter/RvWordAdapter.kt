package developer.abdulahad.mydictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.databinding.ItemWordBinding

class RvWordAdapter(var list: List<MyRoom>,var rvActionWord: RvActionWord) : RecyclerView.Adapter<RvWordAdapter.Vh>() {
    inner class Vh(var itemRv : ItemWordBinding) : RecyclerView.ViewHolder(itemRv.root){
        fun onBind(myRoom: MyRoom , position: Int) {
            itemRv.name.text = myRoom.english
            itemRv.haqida.text = myRoom.uzbek
            itemRv.popup.setOnClickListener {
                rvActionWord.showPopup(itemRv.root,myRoom,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemWordBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] , position)
    }

    override fun getItemCount(): Int = list.size

    interface RvActionWord{
        fun showPopup(view: View, myRoom: MyRoom,position: Int)
    }
}