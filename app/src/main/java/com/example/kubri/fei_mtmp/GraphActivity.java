package com.example.kubri.fei_mtmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Preview preview = findViewById(R.id.graphPreview);
        ArrayList<ParabolaPoint> parabolaData = (ArrayList<ParabolaPoint>) getIntent().getExtras().get(MainActivity.PARABOLA_DATA);
        preview.reDraw(parabolaData);

    }
}
