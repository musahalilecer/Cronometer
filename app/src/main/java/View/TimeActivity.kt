package View

import Fragment.Times
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.musahalilecer.cronometer.R
import com.musahalilecer.cronometer.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeBinding

    private var countDownTimer: CountDownTimer? = null
    private var breakCountDownTimer: CountDownTimer? = null
    private var remainingTime: Long = 0
    private var set: Int = 0

    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var timeSelected: Int = 0

    var INITIAL_REMAINING_TIME : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        set = savedInstanceState?.getInt("set",0) ?: intent.getIntExtra("setValue",0)
        remainingTime = savedInstanceState?.getLong("remainingTime", 0) ?: intent.getLongExtra("value", 0)
        remainingTime = remainingTime * 60 + 1
        updateTime()

        INITIAL_REMAINING_TIME = remainingTime


        startCountDownTimer()

    }
    private fun startCountDownTimer() {
        countDownTimer = object : CountDownTimer((remainingTime * 60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingTime -= 1 // her saniye için remainingTimeInSeconds değerini azaltır
                updateTime()
                if(remainingTime.toInt() == 0){
                    onFinish()
                }
            }

            override fun onFinish() {
                Toast.makeText(this@TimeActivity,"Time is out",Toast.LENGTH_LONG).show()
            }
        }.start()
    }


    private fun updateTime(){

        val hours = remainingTime / 3600
        val minutes = (remainingTime % 3600) / 60
        val seconds = remainingTime % 60

        binding.timeView.text = String.format("%02d:%02d:%02d",hours, minutes, seconds)
    }

    fun start(view: View){
        binding.stopView.isEnabled = true
        startTimer()
        binding.startView.isEnabled = false
    }
    fun startTimer(){
        if (countDownTimer != null) {
            countDownTimer?.start()
            Toast.makeText(this@TimeActivity, "Time is resumed",Toast.LENGTH_SHORT).show()
        }
    }
    fun stop(view: View){
        binding.startView.isEnabled = true
        stopTimer()
        binding.stopView.isEnabled = false


    }
    fun stopTimer(){
        countDownTimer?.cancel()
        breakCountDownTimer?.cancel()
        Toast.makeText(this@TimeActivity, "Time is stopped",Toast.LENGTH_SHORT).show()

        val lastTime = remainingTime
        val bundle = Bundle()
        bundle.putLong("lastTime", lastTime)
        val timesFragment = Times()
        timesFragment.arguments = bundle

        // Fragmentı göster
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, timesFragment)
            .commit()
    }
    fun exit(view: View){
        val builder = AlertDialog.Builder(this@TimeActivity)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure?")

        stopTimer()

        builder.setPositiveButton("Yes") { dialog, _ ->
            val intent = Intent(this@TimeActivity, MenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

            Toast.makeText(this@TimeActivity, "You clicked Yes", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            startTimer()
            Toast.makeText(this@TimeActivity, "You clicked No", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    /*
    currentSet++
                if (currentSet < set) {
                    remainingTime = INITIAL_REMAINING_TIME // Başlangıç değerine geri dön
                    startCountDownTimer() // Set sayısı kadar yeniden başlat
                } else {
                    Toast.makeText(this@TimeActivity, "Time is Over", Toast.LENGTH_LONG).show()
                    val builder = AlertDialog.Builder(this@TimeActivity)
                    builder.setTitle("Restart")
                    builder.setMessage("Do you want to restart")

                    builder.setPositiveButton("Yes") { dialog, _ ->
                        remainingTime = INITIAL_REMAINING_TIME // Başlangıç değerine geri dön
                        countDownTimer?.cancel()
                        startCountDownTimer()
                    }
                    builder.setNegativeButton("No") { dialog, _ ->
                        val intent = Intent(this@TimeActivity, MenuActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    }
                    val dialog = builder.create()
                    dialog.show()
                }
     */
}
