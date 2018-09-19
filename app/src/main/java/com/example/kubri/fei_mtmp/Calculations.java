package com.example.kubri.fei_mtmp;


import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Calculations {


    static List<Pair<Double, Double>> parabola(int angle, int velocity) {
        double xV = Math.sin(Math.toRadians(angle)) * velocity;
        double yV = Math.cos(Math.toRadians(angle)) * velocity;
        List<Pair<Double, Double>> res = new ArrayList<>();
        res.add(new Pair<>(0.0, 0.0));

        double t = 0;

        for (double xS = 0; xS >= 0; xS += .01) {
            t += .01;
            xS = xV * t - 9.81 * t * t;

            double yS = yV * t;

            res.add(new Pair<>(xS, yS));
        }

        return res;
    }

    static String parabolaToString(List<Pair<Double, Double>> in) {
        return in.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
