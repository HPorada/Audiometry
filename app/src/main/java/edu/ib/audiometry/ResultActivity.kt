package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private val folderName = "SavedResults"

    fun getFolderName(): String {
        return folderName
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent: Intent = getIntent()
        val result = getIntent().getSerializableExtra("ResultExtra") as? Result

        var name: TextView = findViewById<TextView>(R.id.tvResult) as TextView

        name.movementMethod = ScrollingMovementMethod()
        name.setText(result.toString())
    }
}