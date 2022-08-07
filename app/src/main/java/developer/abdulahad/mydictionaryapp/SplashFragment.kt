package developer.abdulahad.mydictionaryapp

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulahad.mydictionaryapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    lateinit var handler: Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        var anim = AnimationUtils.loadAnimation(binding.root.context,R.anim.my_anim)
        binding.tvDictionary.startAnimation(anim)

        handler = Handler(requireActivity().mainLooper)

        handler.postDelayed(runnable,2600)

        return binding.root
    }
    private val runnable = object : Runnable{
        override fun run() {
            findNavController().popBackStack()
            findNavController().navigate(R.id.dictinaryFragment)
        }
    }
}