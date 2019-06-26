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
public class RatingCustomWritable implements Writable {
    
    private long count;
    private double rating;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    

    @Override
    public void write(DataOutput d) throws IOException {
        d.writeLong(count);
        d.writeDouble(rating);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        count = di.readLong();
        rating = di.readDouble();
    }

    @Override
    public String toString() {
        return "RatingCustomWritable{" + "count=" + count + ", rating=" + rating + '}';
    }
    
    
    
}
