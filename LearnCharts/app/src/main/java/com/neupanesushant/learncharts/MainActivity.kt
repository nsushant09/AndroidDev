package com.neupanesushant.learncharts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.neupanesushant.learncharts.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var lineDataSet : BarDataSet? = null

    private var startIndex = 0

    val transactionAxisInfoList = listOf(
        TransactionAxisInfo("1", "2143.0", "2022-01-30"),
        TransactionAxisInfo("2", "28.0", "2022-01-31"),
        TransactionAxisInfo("3", "111111.0", "2022-02-01"),
        TransactionAxisInfo("4", "12134.0", "2022-02-02"),
        TransactionAxisInfo("5", "325443.0", "2022-02-03"),
        TransactionAxisInfo("6", "193847.0", "2022-02-04"),
        TransactionAxisInfo("7", "981276.0", "2022-02-05"),
        TransactionAxisInfo("8", "211029.0", "2022-02-06"),
        TransactionAxisInfo("9", "908237.0", "2022-02-07"),
        TransactionAxisInfo("10", "12345.0", "2022-02-08"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLineChart()

    }

    fun setupLineChart(){

        var maximumYAxisValue = 0f;

        if(binding.lineChartView.data == null){
            val dayLabels : MutableList<String> = ArrayList()
            val depositEntry : MutableList<BarEntry> = ArrayList()


            val description = Description()
            description.text = "Description"
            description.textSize = 24f
            description.setPosition(200f, 200f)
            binding.lineChartView.description = description

            for(i in transactionAxisInfoList.indices){
                try{
                    val sdf = SimpleDateFormat("MMM dd", Locale.UK)
                    dayLabels.add(sdf.format(SimpleDateFormat("yyyy-MM-dd", Locale.UK).parse(transactionAxisInfoList[i].day)!!))
                }catch(e : ParseException){
                    e.printStackTrace()
                }
                val y = transactionAxisInfoList[i].yaxis.toFloat()
                if (y > maximumYAxisValue) {
                    maximumYAxisValue = y
                }
                depositEntry.add(BarEntry(i.toFloat(), y))
            }
            val depositDataSet = BarDataSet(depositEntry, "Deposit Entry")
            //            depositDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
//            depositDataSet.lineWidth = 2f
            lineDataSet = depositDataSet.copy() as BarDataSet
            val dataSets: MutableList<IBarDataSet> = java.util.ArrayList()
            dataSets.add(depositDataSet)
            val lineData = BarData(dataSets)
            //Animation
            binding.lineChartView.animateX(800)
            //Description Disabled
            binding.lineChartView.description.isEnabled = false
            //Axis Right Disabled
            binding.lineChartView.axisRight.isEnabled = false
            //disable legend to chart
            val legend = binding.lineChartView.legend
            legend.isEnabled = false


            // Zoom and Scale Disabled
            binding.lineChartView.isDoubleTapToZoomEnabled = false
            binding.lineChartView.setPinchZoom(false)
            binding.lineChartView.setScaleEnabled(false)


            //hide grid lines
            binding.lineChartView.xAxis.setDrawGridLines(false)
            binding.lineChartView.axisLeft.setDrawAxisLine(false)
            binding.lineChartView.axisRight.setDrawAxisLine(false)



            // Set XAxis
            val xAxis = binding.lineChartView.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.axisMinimum = 0f
            xAxis.granularity = 1f // minimum axis-step (interval) is 1
            xAxis.gridColor = android.R.color.transparent
            xAxis.gridLineWidth = 1f
            xAxis.textSize = 8f
            xAxis.axisLineWidth = 1f

            val formatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    if (value > -1 && value < depositEntry.size) {
                        if (startIndex == 0 || startIndex < 0) {
                            // set label from all available statement
                            return dayLabels[value.toInt()]
                        } else if (startIndex + value.toInt() < dayLabels.size) {
                            // only show label starting with the specified index
                            return dayLabels[startIndex + value.toInt()]
                        }
                    }
                    return ""
                }
            }

            xAxis.valueFormatter = formatter
            xAxis.setLabelCount(5, true)

            // Set YAxis
            val yAxis = binding.lineChartView.axisLeft
            yAxis.gridColor = android.R.color.transparent
            yAxis.axisLineColor = R.color.black
            yAxis.gridLineWidth = 1f
            yAxis.textSize = 8f
            yAxis.axisMinimum = 0f
            yAxis.axisMaximum = maximumYAxisValue
            yAxis.labelCount = 5
            yAxis.minWidth = 20f
            yAxis.isEnabled = true



            binding.lineChartView.isAutoScaleMinMaxEnabled = true
            binding.lineChartView.data = lineData
            binding.lineChartView.notifyDataSetChanged()
            binding.lineChartView.invalidate()
            binding.lineChartView.setDrawMarkers(true)
            binding.lineChartView.setTouchEnabled(true)
        }
        

    }
}