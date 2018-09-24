package com.example.kubri.fei_mtmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        TextView textView = findViewById(R.id.parabolaListView);
        ArrayList<ParabolaPoint> parabolaData = (ArrayList<ParabolaPoint>) getIntent().getExtras().get(MainActivity.PARABOLA_DATA);
        String parabolaStr = Calculations.parabolaToString(parabolaData);
        textView.setText(parabolaStr);
    }
}
