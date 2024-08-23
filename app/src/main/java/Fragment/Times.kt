package Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.musahalilecer.cronometer.R
import com.musahalilecer.cronometer.databinding.FragmentTimesBinding

class Times : Fragment() {
    private lateinit var binding: FragmentTimesBinding
    private var lastTime: Long = 0
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimesBinding.inflate(inflater, container, false)
        val view = binding?.root

        arguments?.let {
            lastTime = it.getLong("lastTime")
        }

        updateTime()

        return view
    }
    private fun updateTime(){
        val hours = lastTime/3600
        val minutes = (lastTime % 3600) / 60
        val seconds = lastTime % 60

        binding.textViewLastTime.text = String.format("%02d %02d %02d",hours,minutes,seconds)
    }
}