package developer.abdulahad.mydictionaryapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulahad.mydictionaryapp.ItemPagerInsideFragment
import developer.abdulahad.mydictionaryapp.models.Category

class PageInsideAdapter (var list: List<Category>, fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        return ItemPagerInsideFragment()
    }
}