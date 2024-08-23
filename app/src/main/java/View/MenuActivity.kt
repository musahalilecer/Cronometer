package View


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.musahalilecer.cronometer.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
  //  private var transationTimer: CountDownTimer? = null
  //  private var mainTimer: CountDownTimer? = null
    private var sharedPreferences : SharedPreferences? = null
  //  private var remainingTime : String = "00 : 00"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
    fun start(view: View){
        val stringValue = binding.textView13.text.toString()
        val longValue = stringValue.toLongOrNull() ?: 0.0

        val set = binding.textSet.text.toString()
        val setValue = set.toIntOrNull() ?: 0

        val intent = Intent(this@MenuActivity, TimeActivity::class.java)
        intent.putExtra("value", longValue) // Double değeri ekleyin
        intent.putExtra("setValue",setValue)
        startActivity(intent)

    }
  /*  fun records(view: View){
        val intent = Intent(this@MenuActivity, TimeRecord::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
   */
    fun records(view: View){
        val intent = Intent(this@MenuActivity,Records::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
      startActivity(intent)
    }
}