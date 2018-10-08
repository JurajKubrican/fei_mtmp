package com.example.kubri.fei_mtmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        ArrayList<ParabolaPoint> parabolaData = (ArrayList<ParabolaPoint>) getIntent().getExtras().get(MainActivity.PARABOLA_DATA);


        assert parabolaData != null;
        double max = 0;

        DataPoint[] dataPoints = new DataPoint[parabolaData.size()];
        for (int i = 0; i < parabolaData.size(); i++) {
            ParabolaPoint pp = parabolaData.get(i);
            max = Math.max(max, pp.y);
            dataPoints[i] = new DataPoint(pp.t, pp.y);
        }
        GraphView graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(dataPoints[dataPoints.length - 1].getX() + 3);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMaxY(max + 3);
        graph.getViewport().setMinY(0);


        graph.addSeries(series);

    }
}
