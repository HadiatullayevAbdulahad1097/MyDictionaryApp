package developer.abdulahad.mydictionaryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.mydictionary.Room.Entity.MyRoom
import developer.abdulahad.mydictionaryapp.databinding.ItemRvBinding

class RvAdapter(var list: List<MyRoom>,var click: Click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(myRoom: MyRoom, position: Int) {
            itemRv.name.text = myRoom.english
            itemRv.haqida.text = myRoom.uzbek
            itemRv.card.setOnClickListener {
                click.clickItem(myRoom,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface Click{
        fun clickItem(myRoom: MyRoom,position: Int)
    }
}