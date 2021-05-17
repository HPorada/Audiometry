package edu.ib.audiometry

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class TestActivity : AppCompatActivity() {

    var series: ArrayList<Double> = ArrayList();

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
            intent.putExtra("SERIES", series);
            startActivity(intent)
        }
    }

    private fun newVolume(curVolume: Double): Float {
        return ((1 - (Math.log(maxVolume - curVolume) / Math.log(maxVolume))).toFloat())
    }

    fun onThresholdClick(view: View) {
        curVolume = 0.5 * maxVolume

        var x = 0.0;
        var y = 0.0;

        when (counter) {
            0 -> x = 250.0;
            1 -> x = 500.0;
            2 -> x = 1000.0;
            3 -> x = 2000.0;
            4 -> x = 3000.0;
            5 -> x = 4000.0;
            6 -> x = 6000.0;
            7 -> x = 8000.0;
            else -> x = 0.0;
        }

        y = curVolume;

        /* var z = [x, y];*/

        series.add(x);
        series.add(y);

        /*series[series.size + 1] = x;
        series[series.size + 1] = y;
*/
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
