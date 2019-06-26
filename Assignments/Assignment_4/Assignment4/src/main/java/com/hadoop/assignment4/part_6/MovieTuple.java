/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_6;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author ankit
 */
public class MovieTuple implements Writable{
    
    private double ratingMedian;
    private double ratingStdDev;

    public double getRatingMedian() {
        return ratingMedian;
    }

    public void setRatingMedian(double ratingMedian) {
        this.ratingMedian = ratingMedian;
    }

    public double getRatingStdDev() {
        return ratingStdDev;
    }

    public void setRatingStdDev(double ratingStdDev) {
        this.ratingStdDev = ratingStdDev;
    }
    
    
    
    

    @Override
    public void write(DataOutput out) throws IOException {
       out.writeDouble(ratingStdDev);
       out.writeDouble(ratingMedian);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        ratingStdDev = in.readDouble();
        ratingStdDev = in.readDouble();

    }

    @Override
    public String toString() {
        return "ratingMedian=" + ratingMedian + ", ratingStdDev=" + ratingStdDev;
    }
        
}
