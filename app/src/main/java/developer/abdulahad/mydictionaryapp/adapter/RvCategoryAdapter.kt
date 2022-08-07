package developer.abdulahad.mydictionaryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulahad.mydictionaryapp.models.Category
import developer.abdulahad.mydictionaryapp.databinding.ItemCategoryBinding

class RvCategoryAdapter(var list: List<Category>, var rvAction: RvAction) : RecyclerView.Adapter<RvCategoryAdapter.Vh>() {
    inner class Vh(var itemRv : ItemCategoryBinding) : RecyclerView.ViewHolder(itemRv.root){
        fun onBind(category: Category, position: Int) {
            itemRv.name.text = category.category
            itemRv.popup.setOnClickListener {
                rvAction.showPopup(itemRv.root,category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] , position)
    }

    override fun getItemCount(): Int = list.size

    interface RvAction{
        fun showPopup(view: View,category: Category)
    }
}