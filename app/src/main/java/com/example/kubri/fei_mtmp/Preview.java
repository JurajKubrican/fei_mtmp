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

public class Preview extends View {


    public Preview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }


    private Paint mParabolaPaint;
    private List<Pair<ParabolaPoint, ParabolaPoint>> parabolaData = new ArrayList<>();

    private void init() {
        mParabolaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mParabolaPaint.setStyle(Paint.Style.FILL);
        mParabolaPaint.setStrokeWidth(5);
        mParabolaPaint.setColor(Color.LTGRAY);
    }

    public void reDraw(List<ParabolaPoint> data, double scale) {
        final List<ParabolaPoint> data2 = data.stream().map(item -> new ParabolaPoint(item.x * scale, item.y * scale, item.t * scale)).collect(Collectors.toList());
        this.parabolaData = IntStream.range(1, data.size())
                .mapToObj(i -> new Pair<>(data2.get(i - 1), data2.get(i)))
                .collect(Collectors.toList());

        invalidate();
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasHeight = canvas.getHeight();

        this.parabolaData.forEach(item -> {
            ParabolaPoint start = item.first;
            ParabolaPoint end = item.second;
            canvas.drawLine(start.x.floatValue(), canvasHeight - start.y.floatValue(), end.x.floatValue(), canvasHeight - end.y.floatValue(), mParabolaPaint);
        });
    }


}
