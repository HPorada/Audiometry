package edu.ib.audiometry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {

    private val folderName = "SavedResults"
    private var graphData: LineGraphSeries<DataPoint> = LineGraphSeries();
    private var values: String? = null;

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

        graph.setTitle("Audiogram");

        /*val gridLabel: GridLabelRenderer = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Hz");
        gridLabel.setVerticalAxisTitle("dB");*/

        graph.addSeries(graphData)

        values = displayValues(series);
        text.setText(values);

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

    fun displayValues(series: ArrayList<Double>): String {
        var check = true;
        var x = 0.0;
        var y = 0.0;
        var result = "";

        for (item: Double in series) {
            if (check == true) {
                x = item;
                check = false;
            } else {
                y = item;
                check = true;
                result = result + x.toString() + "\t" + y.toString() + "\n";
            }
        }
        return result;
    }

    fun onSaveClick(view: View) {
      /*  var saveBtn: Button = findViewById<Button>(R.id.btnSave) as Button;*/

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val name = formatter.format(date)

        var myExternalFile: File = File(getExternalFilesDir(folderName), name)

        if (values != null) {
            try {
                FileOutputStream(myExternalFile).use { os ->
                    os.write(values!!.toByteArray())
/*
                    println(values!!.toByteArray())
*/
                    os.close()
                    Toast.makeText(this, "Test saved.", Toast.LENGTH_LONG).show()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "The test result is empty, retake the test.", Toast.LENGTH_LONG)
                .show()
        }
    }
}