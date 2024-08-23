package View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.musahalilecer.cronometer.databinding.ActivityTransationBinding

class TransationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransationBinding
    var countDownTimer : CountDownTimer? = null
    private var handler : Handler? = null
    private var progressStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countDownTimer = object : CountDownTimer(3000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.textView3.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
            //    val intent = Intent(this@TransationActivity,TimeActivity::class.java)
            //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            //    startActivity(intent)
            }
        }.start()

        // Zamanlayıcı başlat
        handler?.postDelayed(runnable, 1000) // 1 saniyede bir güncelleme yap

        // İlerleme durumu dinamik olarak güncellenirse, ProgressBar'ın indeterminate modunu kapatın
        binding.progressBar2.isIndeterminate = false

    /*    var doubleValue2 = intent.getDoubleExtra("doubleValue", 0.0)
        var remainingTime2 = (doubleValue2 * 60 * 1000).toLong()

    //    val stringValue = remainingTime2
    //    val doubleValue2 = stringValue.toDouble()
        val intent = Intent(this@TransationActivity, TimeActivity::class.java)
        intent.putExtra("doubleValue2", doubleValue2)
        startActivity(intent)   */

    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            progressStatus++
            binding.progressBar2.progress = progressStatus

        /*    if (progressStatus < binding.progressBar7.max) {
                // Zamanlayıcıyı 1 saniye sonra tekrar çalıştır
                handler?.postDelayed(this, 1000)
            } else {
                // Zamanlayıcı tamamlandığında yapılacak işlemler
            }

         */
        }

    }
}