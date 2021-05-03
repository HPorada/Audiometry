package edu.ib.audiometry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val startButton = findViewById<Button>(R.id.btnTest)
        startButton.setOnClickListener{
            val intent = Intent(this,TestActivity::class.java)
            startActivity(intent)
        }*/
    }

    fun onStartTestClick(view: View) {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    fun onSavedTestsClick(view: View) {}
    fun onAboutClick(view: View) {}
    fun onArticlesClick(view: View) {}
}