package com.example.kubri.fei_mtmp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parabola extends View {


    public Parabola(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }


    private Paint mParabolaPaint;
    private List<Pair<Pair<Double, Double>, Pair<Double, Double>>> parabolaData = new ArrayList<>();

    private void init() {
        mParabolaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mParabolaPaint.setStyle(Paint.Style.FILL);
        mParabolaPaint.setStrokeWidth(5);
        mParabolaPaint.setColor(Color.LTGRAY);
    }

    public void reDraw(List<Pair<Double, Double>> data) {

        this.parabolaData = IntStream.range(1, data.size())
                .mapToObj(i -> new Pair<>(data.get(i - 1), data.get(i)))
                .collect(Collectors.toList());

        invalidate();
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasHeight = canvas.getHeight();

        this.parabolaData.forEach(item -> {
            Pair<Double, Double> start = item.first;
            Pair<Double, Double> end = item.second;
            canvas.drawLine(start.second.floatValue(), canvasHeight - start.first.floatValue(), end.second.floatValue(), canvasHeight - end.first.floatValue(), mParabolaPaint);
        });
    }


}
