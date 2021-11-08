package com.my.entity;

import lombok.Data;

@Data
public class SerialModel implements Comparable {

    public SerialModel(int priority) {
        setParity(priority);
    }

    private int parity;

    @Override
    public int compareTo(Object oSerialModel) {
        if (this.parity < ((SerialModel) oSerialModel).parity) {
            return 1;
        } else if (this.parity == ((SerialModel) oSerialModel).parity) {
            return 0;
        } else
            return -1;
    }
}
