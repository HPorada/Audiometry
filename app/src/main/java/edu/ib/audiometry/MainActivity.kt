package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    var results: ArrayList<Result> = ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartTestClick(view: View) {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    fun onSavedTestsClick(view: View) {
        createResultList();

        val intent = Intent(this, ResultsRecyclerActivity::class.java)
        intent.putExtra("RESULTS", results)

        startActivity(intent)
    }

    fun onAboutClick(view: View) {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    fun onArticlesClick(view: View) {
        val intent = Intent(this, ArticlesActivity::class.java)
        startActivity(intent)
    }

    fun createResultList() {
        results.clear()

        var result = ResultActivity();
        val file = getExternalFilesDir(result.getFolderName())
        val fileListing = file!!.listFiles()
        if (fileListing != null) {
            for (i in fileListing.indices) {
                val str = StringBuilder()
                try {
                    BufferedReader(FileReader(fileListing[i])).use { bufr ->
                        val fileName = fileListing[i].name
                        var text: String?
                        while (bufr.readLine().also { text = it } != null) {
                            str.append(text)
                        }
                        val result = Result(fileName, str.toString())
                        results.add(result)

                        /* val date = getDate(fileName)
                         val recipe = Result(fileName, date , str.toString())
                         results.add(recipe)*/
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(this, "File not found.", Toast.LENGTH_LONG).show()
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    /*fun getDate(name: String): LocalDateTime {
        *//*val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(name, formatter);*//*
        val sep = name.split("\\s".toRegex())[0]

        val dd = Integer.parseInt(sep[0].toString());

        var mm = 0;

        when (sep[1].toString()) {
            "sty" -> mm = 1
            "lut" -> mm = 2
            "mar" -> mm = 3
            "kwi" -> mm = 4
            "maj" -> mm = 5
            "cze" -> mm = 6
            "lip" -> mm = 7
            "sie" -> mm = 8
            "wrz" -> mm = 9
            "paź" -> mm = 10
            "lis" -> mm = 11
            "gru" -> mm = 12
        }

        val yy = Integer.parseInt(sep[2].toString())

        val hour = Integer.parseInt(sep[3].toString())

        val min = Integer.parseInt(sep[4].toString())

        val sec = Integer.parseInt(sep[5].toString())

        return LocalDateTime.of(yy, mm, dd, hour, min, sec);
    }*/
}