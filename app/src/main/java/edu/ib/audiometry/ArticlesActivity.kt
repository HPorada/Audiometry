package edu.ib.audiometry

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ArticlesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hygiene)

        var tvHygiene: TextView = findViewById<TextView>(R.id.tvHygiene) as TextView;

        tvHygiene.movementMethod = ScrollingMovementMethod()
    }
}