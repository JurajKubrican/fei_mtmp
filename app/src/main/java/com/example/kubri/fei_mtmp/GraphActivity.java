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

        DataPoint[] dataPoints = new DataPoint[parabolaData.size()];
        for (int i = 0; i < parabolaData.size(); i++) {
            ParabolaPoint pp = parabolaData.get(i);
            dataPoints[i] = new DataPoint(pp.t, pp.y);
        }
        GraphView graph = findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dataPoints);
        graph.addSeries(series);

    }
}
