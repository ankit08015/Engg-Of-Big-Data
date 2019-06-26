package com.hadoop.assignment4.part_7;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class MedianSdTuple implements Writable {

    private double median;
    private double sd;

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getSd() {
        return sd;
    }

    public void setSd(double sd) {
        this.sd = sd;
    }
    
    

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeDouble(sd);
        d.writeDouble(median);
       
    }

    @Override
    public void readFields(DataInput di) throws IOException {
       sd = di.readDouble();
       median= di.readDouble();
    }

    @Override
    public String toString() {
        return "median=" + median + ", sd=" + sd;
    }
    
    

    
}
