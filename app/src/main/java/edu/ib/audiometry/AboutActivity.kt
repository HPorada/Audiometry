package edu.ib.audiometry

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        var tvAudiometry: TextView = findViewById<TextView>(R.id.tvAudiometry) as TextView;

        tvAudiometry.movementMethod = ScrollingMovementMethod()
    }
}