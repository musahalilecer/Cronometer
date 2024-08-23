package View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.musahalilecer.cronometer.R
import com.musahalilecer.cronometer.databinding.ActivityRecordsBinding

class Records : AppCompatActivity() {
    private lateinit var binding: ActivityRecordsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
    fun back(view: View){
        val intent = Intent(this@Records,MenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}