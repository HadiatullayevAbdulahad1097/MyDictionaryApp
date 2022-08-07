package developer.abdulahad.mydictionaryapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulahad.mydictionaryapp.PageKategoryFragment
import developer.abdulahad.mydictionaryapp.PageSozlarFragment

class PagerCategoryAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PageKategoryFragment()
            else -> PageSozlarFragment()
        }
    }
}