package com.hadoop.assignment4.part_5;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AvgStockPriceTuple implements Writable {

    private double avgStockPrice;
    private long count;

    public double getAvgStockPrice() {
        return avgStockPrice;
    }

    public void setAvgStockPrice(double avgStockPrice) {
        this.avgStockPrice = avgStockPrice;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(avgStockPrice);
        out.writeLong(count);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        avgStockPrice = in.readDouble();
        count = in.readLong();
    }

    @Override
    public String toString() {
        return  "avgStockPrice=" + avgStockPrice;
                // +", count=" + count ;
    }
}
