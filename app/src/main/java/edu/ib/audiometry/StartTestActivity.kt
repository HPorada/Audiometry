package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StartTestActivity : AppCompatActivity() {

    private var option: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_test)

        var tvInfo: TextView = findViewById<TextView>(R.id.tvInstruction) as TextView;

        tvInfo.movementMethod = ScrollingMovementMethod()
    }

    fun onStartClick(view: View) {

        var checkEarphones: CheckBox = findViewById<CheckBox>(R.id.checkEarphones) as CheckBox;
        var checkSpeaker: CheckBox = findViewById<CheckBox>(R.id.checkSpeaker) as CheckBox;

        if (checkEarphones.isChecked() && checkSpeaker.isChecked()) {
            Toast.makeText(
                this,
                "Wybierz tylko jedną opcję przeprowadzenia testu.",
                Toast.LENGTH_LONG
            ).show()

        } else if (!checkEarphones.isChecked() && !checkSpeaker.isChecked()) {
            Toast.makeText(this, "Wybierz opcję przeprowadzenia testu.", Toast.LENGTH_LONG).show()

        } else if (checkEarphones.isChecked() && !checkSpeaker.isChecked()) {
            option = "earphones";

            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra("OPTION", option)

            startActivity(intent)

        } else if (!checkEarphones.isChecked() && checkSpeaker.isChecked()) {
            option = "speaker";

            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra("OPTION", option)

            startActivity(intent)
        }

    }


}