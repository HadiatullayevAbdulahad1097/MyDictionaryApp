package developer.abdulahad.mydictionaryapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulahad.mydictionaryapp.InsideDictionaryFragment
import developer.abdulahad.mydictionaryapp.LikedFragment

class PageAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> InsideDictionaryFragment()
            else -> LikedFragment()
        }
    }
}