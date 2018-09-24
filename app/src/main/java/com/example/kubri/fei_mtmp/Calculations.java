package com.example.kubri.fei_mtmp;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

class Calculations {


    static ArrayList<ParabolaPoint> parabola(int angle, int velocity) {
        double xV = Math.sin(Math.toRadians(angle)) * velocity;
        double yV = Math.cos(Math.toRadians(angle)) * velocity;
        ArrayList<ParabolaPoint> res = new ArrayList<>();
        res.add(new ParabolaPoint(0.0, 0.0, 0.0));

        double t = 0;

        for (double yS = 0; yS >= 0; t += .1) {
            yS = xV * t - 9.81 * t * t / 2;
            double xS = yV * t;
            res.add(new ParabolaPoint(xS, yS, t));
        }

        return res;
    }

    static String parabolaToString(List<ParabolaPoint> in) {
        return in.stream().map(item ->
                "x:" + String.format(Locale.US, "%.2f", item.x) +
                        " y:" + String.format(Locale.US, "%.2f", item.y) +
                        " t:" + String.format(Locale.US, "%.2f", item.t))
                .collect(Collectors.joining("\n"));
    }
}
