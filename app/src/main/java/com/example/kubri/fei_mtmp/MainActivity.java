package com.example.kubri.fei_mtmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import static com.example.kubri.fei_mtmp.Calculations.*;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SeekBar angleSeek = findViewById(R.id.seekBarAngle);
        final TextView angleText = findViewById(R.id.editTextAngle);
        final TextView resText = findViewById(R.id.resultText);
        final SeekBar veloSeek = findViewById(R.id.seekBarVelocity);
        final TextView veloText = findViewById(R.id.editTextVelocity);


        angleSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int angle = seekBar.getProgress();
                int velocity = veloSeek.getProgress();
                angleText.setText(String.valueOf(angle));
                resText.setText(parabolaToString(parabola(angle, velocity)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        veloSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int velocity = seekBar.getProgress();
                int angle = angleSeek.getProgress();
                veloText.setText(String.valueOf(angle));
                resText.setText(parabolaToString(parabola(angle, velocity)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


//        View graphView = findViewById()

    }


}
