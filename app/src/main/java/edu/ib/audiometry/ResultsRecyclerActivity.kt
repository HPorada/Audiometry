package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class ResultsRecyclerActivity : AppCompatActivity(), OnItemClickListener {
    lateinit var resultList: ArrayList<Result>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tests_list)

        resultList = getIntent().getSerializableExtra("RESULTS") as ArrayList<Result>
        /*
        resultList = MainActivity.results*/
        //Collections.sort(resultList)
        val rvResults = findViewById<RecyclerView>(R.id.rvResultList)
        rvResults.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val adapter: RecyclerView.Adapter<*> = ResultsAdapter(resultList, this)
        rvResults.layoutManager = layoutManager
        rvResults.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SERIES", createArray(getValues(position)))
        startActivity(intent)
    }

    fun deleteResult(view: View?, position: Int) {
        val name = resultList[position].getFileName();
        var deleted = false;

        var result = ResultActivity();
        val file = getExternalFilesDir(result.getFolderName())

        val fileListing = file!!.listFiles()

        if (fileListing != null) {
            for (i in fileListing.indices) {
                try {
                    val fileName = fileListing[i].name
                    if (name == fileName) {
                        deleted = fileListing[i].delete()
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

        if (deleted) {
            Toast.makeText(this, "File successfully deleted.", Toast.LENGTH_LONG).show()
        }

        /*val fileListing = file!!.listFiles()
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

                        *//* val date = getDate(fileName)
                         val recipe = Result(fileName, date , str.toString())
                         results.add(recipe)*//*
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
        }*/
    }

    fun getValues(position: Int): String {
        val values = resultList[position].getVolumes();
        return values;
    }

    fun createArray(values: String): ArrayList<Double> {
        var array = values.split("\t")
        var series: ArrayList<Double> = ArrayList()

        for (item: String in array) {
            if (item.isNotEmpty()) {
                var x = item.toDouble();
                series.add(x);
            }
        }

        return series;
    }

    fun deleteResult(view: View) {}

    /*fun onSearchClick(view: View?) {
        val edt = findViewById<View>(R.id.edtSearch2) as EditText
        val search = edt.text.toString()
        var recipe = Recipe()
        for (i in recipeList!!.indices) {
            if (search == recipeList!![i].getName()) {
                recipe = recipeList!![i]
                val intent = Intent(this, RecipeActivity::class.java)
                intent.putExtra("RecipeExtra", recipe)
                startActivity(intent)
            }
        }
    }*/
}
