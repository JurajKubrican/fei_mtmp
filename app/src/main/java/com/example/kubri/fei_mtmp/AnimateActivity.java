package com.example.kubri.fei_mtmp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

public class AnimateActivity extends AppCompatActivity {

    ArrayList<ParabolaPoint> parabolaData;
    SeekBar seekBarAnimate;
    Button buttonAnimate;
    boolean animationOn = true;
    boolean seekOn = false;


    FrameLayout projectileView;
    Double initialAngle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        projectileView = findViewById(R.id.projectileFrame);
        parabolaData = (ArrayList<ParabolaPoint>) getIntent().getExtras().get(MainActivity.PARABOLA_DATA);


        ParabolaPoint p1 = parabolaData.get(0);
        ParabolaPoint p2 = parabolaData.get(1);
        initialAngle = Math.atan2(p1.y - p2.y, p1.x - p2.x) / Math.PI * 180 - 45;

        animateFrame(0);

        buttonAnimate = findViewById(R.id.animateButton);
        buttonAnimate.setOnClickListener(v -> {
            buttonAnimate.setEnabled(false);
            animationOn = true;
            animateFrame(seekBarAnimate.getProgress());
        });

        seekBarAnimate = findViewById(R.id.seekBarAnimate);
        seekBarAnimate.setMax(parabolaData.size() - 1);
        seekBarAnimate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (seekOn) {
                    buttonAnimate.setEnabled(true);
                    animationOn = false;
                    animateFrame(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekOn = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekOn = false;
            }
        });
    }


    void animateFrame(final int index) {
        ParabolaPoint p1 = parabolaData.get(index);
        ParabolaPoint p2 = parabolaData.get(index + 1);


        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX", p2.x.floatValue());
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY", -p2.y.floatValue());
        ObjectAnimator translateAnimator = ObjectAnimator.ofPropertyValuesHolder(projectileView, pvhX, pvhY);

        Double rad = -Math.atan2(p1.y - p2.y, p1.x - p2.x) / Math.PI * 180 + 180 - initialAngle;
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(projectileView, "rotation", rad.floatValue());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateAnimator, rotateAnimator);
        animatorSet.setDuration(10);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (index + 1 < parabolaData.size() - 1 && animationOn) {
                    seekBarAnimate.setProgress(index);
                    animateFrame(index + 1);
                }
            }
        });
        animatorSet.start();
    }


}
