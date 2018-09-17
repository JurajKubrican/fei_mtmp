package com.example.kubri.fei_mtmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SeekBar angleSeek = findViewById(R.id.seekBarAngle);
        final EditText angleText = findViewById(R.id.editTextAngle);

        angleSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                angleText.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        angleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                angleSeek.setProgress(Integer.parseInt(s.toString()), true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final SeekBar veloSeek = findViewById(R.id.seekBarVelocity);
        final EditText veloText = findViewById(R.id.editTextVelocity);

        veloSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                veloText.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        veloText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                veloSeek.setProgress(Integer.parseInt(s.toString()), true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


}
