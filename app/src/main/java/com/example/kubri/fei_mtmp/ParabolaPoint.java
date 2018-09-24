package com.example.kubri.fei_mtmp;


import java.io.Serializable;

class ParabolaPoint implements Serializable {

    ParabolaPoint(Double x, Double y, Double t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }

    Double x;
    Double y;
    Double t;

}
