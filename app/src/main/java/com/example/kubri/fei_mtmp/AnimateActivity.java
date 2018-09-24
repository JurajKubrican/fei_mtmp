package com.example.kubri.fei_mtmp;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        ImageView projectileView = findViewById(R.id.projectileView);

        ObjectAnimator animation = ObjectAnimator.ofFloat(projectileView, "translationX", 100f);
        animation.setDuration(2000);
        animation.start();
    }
}
