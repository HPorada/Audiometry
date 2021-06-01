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

        //var saveBtn: Button = findViewById<Button>(R.id.btnSave) as Button;

       /* var tvHygiene: TextView = findViewById<TextView>(R.id.tvHygiene) as TextView;

        tvHygiene.movementMethod = ScrollingMovementMethod()

        var r: BufferedReader? = null;

        try {
            val inStream = getResources().openRawResource(R.raw.hearing);
            r = BufferedReader(InputStreamReader(getAssets().open(R.values.hearing)));

            var line: String;

            while (line = r.readLine() != null) {
                text.append(line);
            }

        } catch (var e: IOException) {
            Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        } finally {
            if (r != null) {

            }
        }*/

    }
}