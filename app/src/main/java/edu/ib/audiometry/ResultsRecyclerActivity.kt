package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

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
        //intent.putExtra("ResultExtra", resultList!![position])
        startActivity(intent)
    }

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
