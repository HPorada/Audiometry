package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class ResultActivity : AppCompatActivity() {

    private val folderName = "SavedResults"
    private var graphData: LineGraphSeries<DataPoint> = LineGraphSeries();

    fun getFolderName(): String {
        return folderName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent: Intent = getIntent();
        val series: ArrayList<Double> =
            getIntent().getSerializableExtra("SERIES") as ArrayList<Double>
        //val result = getIntent().getSerializableExtra("SERIES") as? Result

        var text = findViewById<TextView>(R.id.tvResult) as TextView
        var graph = findViewById<GraphView>(R.id.graph) as GraphView

        makeSeries(series);

        graph.addSeries(graphData)

        /*name.movementMethod = ScrollingMovementMethod()
        name.setText(result.toString())*/

        /*series.appendData(DataPoint(x, y), true, 8);*/
    }

    fun makeSeries(series: ArrayList<Double>) {
        var check = true;
        var x = 0.0;
        var y = 0.0;

        for (item: Double in series) {
            if (check == true) {
                x = item;
                check = false;
            } else {
                y = item;
                check = true;
                graphData.appendData(DataPoint(x, y), true, 8)
            }
        }
    }
}