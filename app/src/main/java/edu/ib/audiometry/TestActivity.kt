package edu.ib.audiometry

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class TestActivity : AppCompatActivity() {

    var counter = 0
    val recordings = arrayOf<Int>(
        R.raw.hz250,
        R.raw.hz500,
        R.raw.hz1000,
        R.raw.hz2000,
        R.raw.hz3000,
        R.raw.hz4000,
        R.raw.hz6000,
        R.raw.hz8000
    )
    var maxVolume = 120.0
    var curVolume = 40.0
    var volume = newVolume(curVolume)

    var left = 0.0
    var right = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var mgr: AudioManager? = null

        mgr = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        mgr.setStreamVolume(
            AudioManager.STREAM_MUSIC,
            10,
            AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE
        )
        maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toDouble()

        curVolume = 0.5 * maxVolume
        counter = 0
    }

    fun onStartClick(view: View) {

        if (counter < recordings.size) {
            try {
                volume = newVolume(curVolume)

                val mediaPlayer = MediaPlayer.create(this, recordings.get(counter))
                mediaPlayer.isLooping = false
                mediaPlayer.setVolume(volume, volume)
                mediaPlayer.start()

                mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener {
                    fun onCompletion(mediaPlayer: MediaPlayer) {
                        mediaPlayer.stop()
                        mediaPlayer.release()
                    }
                })
            } catch (e: Exception) {

            }
        } else {
            val toast = Toast.makeText(this, "Badanie ukończone", Toast.LENGTH_LONG)
            toast.show()

            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun newVolume(curVolume: Double): Float {
        return ((1 - (Math.log(maxVolume - curVolume) / Math.log(maxVolume))).toFloat())
    }

    fun onThresholdClick(view: View) {
        curVolume = 0.5 * maxVolume
        counter++
    }

    fun onHearClick(view: View) {
        if (curVolume > 0)
            curVolume -= 0.05 * maxVolume
        else {
            counter++
            curVolume = 0.5 * maxVolume
        }
    }


    fun onNotHearClick(view: View) {
        if (curVolume < maxVolume)
            curVolume += 0.05 * maxVolume
        else {
            counter++
            curVolume = 0.5 * maxVolume
        }
    }
}
