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
        val intent = Intent(this, ResultsRecyclerActivity::class.java)
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



    /*fun createResultList() {
        results.clear()
        val file = getExternalFilesDir(ResultActivity::getFolderName())
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
                        // val recipe = Result(fileName, str.toString())
                        // results.add(recipe)
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
    }*/
}