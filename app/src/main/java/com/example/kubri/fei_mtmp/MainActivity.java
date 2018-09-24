package com.example.kubri.fei_mtmp;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.kubri.fei_mtmp.Calculations.*;


public class MainActivity extends AppCompatActivity {

    public static final String PARABOLA_DATA = "com.example.myfirstapp.PARABOLA_DATA";

    private ArrayList<ParabolaPoint> parabolaData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SeekBar angleSeek = findViewById(R.id.seekBarAngle);
        final TextView angleText = findViewById(R.id.editTextAngle);
        final SeekBar veloSeek = findViewById(R.id.seekBarVelocity);
        final TextView veloText = findViewById(R.id.editTextVelocity);

        final Preview preview = findViewById(R.id.mainPreview);




        angleSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int angle = seekBar.getProgress();
                int velocity = veloSeek.getProgress();
                angleText.setText(String.valueOf(angle));
                setParabolaData(parabola(angle, velocity));
                preview.reDrawPreview(getParabolaData());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        veloSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int velocity = seekBar.getProgress();
                int angle = angleSeek.getProgress();
                veloText.setText(String.valueOf(velocity));
                setParabolaData(parabola(angle, velocity));
                preview.reDrawPreview(getParabolaData());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }


        });


        int angle = angleSeek.getProgress();
        int velocity = veloSeek.getProgress();

        setParabolaData(parabola(angle, velocity));
        preview.reDrawPreview(getParabolaData());


        // NAV buttons
        Button ButtonAnimate = findViewById(R.id.buttonAnimate);
        ButtonAnimate.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AnimateActivity.class);
            intent.putExtra(PARABOLA_DATA, getParabolaData());
            getApplicationContext().startActivity(intent);
        });

        Button ButtonList = findViewById(R.id.buttonList);
        ButtonList.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            intent.putExtra(PARABOLA_DATA, getParabolaData());
            getApplicationContext().startActivity(intent);
        });

        Button ButtonGraph = findViewById(R.id.buttonGraph);
        ButtonGraph.setOnClickListener((View v) -> {
            Intent intent = new Intent(getApplicationContext(), GraphActivity.class);
            ArrayList<ParabolaPoint> data = getParabolaData();
            intent.putExtra(PARABOLA_DATA, data);
            getApplicationContext().startActivity(intent);
        });


    }


    public ArrayList<ParabolaPoint> getParabolaData() {
        return parabolaData;
    }

    public void setParabolaData(ArrayList<ParabolaPoint> parabolaData) {
        this.parabolaData = parabolaData;
    }
}
