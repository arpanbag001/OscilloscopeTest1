package com.example.oscilloscopetest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ValueLineChart valueLineChart;
    ArrayList<Float> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valueLineChart = findViewById(R.id.cubiclinechart);

        valueLineChart.setShowIndicator(false);

        arrayList = new ArrayList<>();
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);
        arrayList.add(0f);

        refreshGraph();

        final Random rand = new Random();

        CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                arrayList.remove(0);
                arrayList.add(rand.nextFloat());
                refreshGraph();
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    private void refreshGraph() {
        valueLineChart.clearChart();
        valueLineChart.addStandardValue(0.8f);
        ValueLineSeries valueLineSeries = new ValueLineSeries();
        valueLineSeries.setColor(0xFF56B7F1);
        for (float f : arrayList
        ) {
            valueLineSeries.addPoint(new ValueLinePoint(String.format(Locale.getDefault(), "%.2f", f), f));
        }
        valueLineChart.addSeries(valueLineSeries);
    }
}
