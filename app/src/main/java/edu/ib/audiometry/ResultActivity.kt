package edu.ib.audiometry

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ResultActivity : AppCompatActivity() {

    private val folderName = "SavedResults"
    private var graphData: LineGraphSeries<DataPoint> = LineGraphSeries();
    private var graphData2: LineGraphSeries<DataPoint> = LineGraphSeries();
    private var values: String? = null;
    private var valToSave: String? = null;

    fun getFolderName(): String {
        return folderName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent: Intent = getIntent();
        val series: ArrayList<Double> =
            getIntent().getSerializableExtra("SERIES") as ArrayList<Double>

        var text = findViewById<TextView>(R.id.tvResult) as TextView
        var graph = findViewById<GraphView>(R.id.graph) as GraphView

        graphData2.color = Color.RED;

        makeSeries(series);

        graph.setTitle("Audiogram");

        graph.addSeries(graphData)

        if (series.size > 8) {
            graph.addSeries(graphData2)
        }

        values = displayValues(series);
        text.setText(values);

        valToSave = saveValues(series);

        text.movementMethod = ScrollingMovementMethod()

    }

    fun makeSeries(series: ArrayList<Double>) {
        var check = true;
        var x = 0.0;
        var y = 0.0;
        var counter = 0;

        if (series.size == 8) {
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
        } else {
            for (item: Double in series) {
                if (check == true) {
                    x = item;
                    check = false;
                } else {
                    y = item;
                    check = true;
                    if (counter < 16) {
                        graphData.appendData(DataPoint(x, y), true, 8)
                    } else {
                        graphData2.appendData(DataPoint(x, y), true, 8)
                    }
                }
                counter++;
            }
        }
    }

    fun displayValues(series: ArrayList<Double>): String {
        var check = true;
        var x = 0.0;
        var y = 0.0;
        var result = "";
        var message = "";
        var counter = 0;

        if (series.size < 16) {
            for (item: Double in series) {
                if (check == true) {
                    x = item;
                    check = false;
                } else {
                    y = item;

                    if (y <= 25) message = "słuch w normie"
                    else if (y > 25 && y <= 40) message = "łagodny ubytek słuchu"
                    else if (y > 40 && y <= 55) message = "umiarkowany ubytek słuchu"
                    else if (y > 55 && y <= 70) message = "umiarkowanie poważny ubytek słuchu"
                    else if (y > 70 && y <= 90) message = "poważny ubytek słuchu"
                    else if (y > 90 && y <= 120) message = "głęboki ubytek słuchu"
                    else message = "brak informacji"

                    check = true;
                    result =
                        result + "Dla częstotliwości " + x.toString() + " Hz: " + message + ". (" + ((y * 100).toInt() / 100.0).toString() + " dB)" + "\n\n";
                }
            }
        } else {
            result = "PRAWE UCHO:" + "\n"
            for (item: Double in series) {
                if (check == true) {
                    x = item;
                    check = false;
                } else {
                    y = item;

                    if (y <= 25) message = "słuch w normie"
                    else if (y > 25 && y <= 40) message = "łagodny ubytek słuchu"
                    else if (y > 40 && y <= 55) message = "umiarkowany ubytek słuchu"
                    else if (y > 55 && y <= 70) message = "umiarkowanie poważny ubytek słuchu"
                    else if (y > 70 && y <= 90) message = "poważny ubytek słuchu"
                    else if (y > 90 && y <= 120) message = "głęboki ubytek słuchu"
                    else message = "brak informacji"

                    check = true;

                    if (counter == 15) {
                        result = result + "LEWE UCHO:" + "\n"
                    }
                    result =
                        result + "Dla częstotliwości " + x.toString() + " Hz: " + message + ". (" + ((y * 100).toInt() / 100.0).toString() + " dB)" + "\n\n";
                }
                counter++;
            }

        }
        return result;
    }

    fun saveValues(series: ArrayList<Double>): String {
        var x = 0.0;
        var result = "";

        for (item: Double in series) {
            x = item;
            result = result + x.toString() + "\t"
        }
        return result;
    }

    fun onSaveClick(view: View) {

        val date = Calendar.getInstance().time
        val formatter = SimpleDateFormat.getDateTimeInstance()
        val name = formatter.format(date)

        var myExternalFile: File = File(getExternalFilesDir(folderName), name)

        if (values != null) {
            try {
                FileOutputStream(myExternalFile).use { os ->
                    os.write(valToSave?.toByteArray())
                    os.close()
                    Toast.makeText(this, "Badanie pomyślnie zapisane.", Toast.LENGTH_LONG).show()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Coś poszło nie tak. Spróbuj ponownie.", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            Toast.makeText(this, "Wynik testu jest pusty. Powtórz badanie.", Toast.LENGTH_LONG)
                .show()
        }
    }
}