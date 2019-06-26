/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Writable;

/**
 *
 * @author ankit
 */
public class MinMaxStockTuple implements Writable{
    
    private Date dateMaxStockVolume = new Date();
    private Date dateMinStockVolume = new Date();
    private double maxStockPriceClose;
    private long maxStockVolume;

    private final static SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");


    public Date getDateMaxStockVolume() {
        return dateMaxStockVolume;
    }

    public void setDateMaxStockVolume(Date dateMaxStockVolume) {
        this.dateMaxStockVolume = dateMaxStockVolume;
    }

    public Date getDateMinStockVolume() {
        return dateMinStockVolume;
    }

    public void setDateMinStockVolume(Date dateMinStockVolume) {
        this.dateMinStockVolume = dateMinStockVolume;
    }

    public double getMaxStockPriceClose() {
        return maxStockPriceClose;
    }

    public void setMaxStockPriceClose(double maxStockPriceClose) {
        this.maxStockPriceClose = maxStockPriceClose;
    }

    public long getMaxStockVolume() {
        return maxStockVolume;
    }

    public void setMaxStockVolume(long maxStockVolume) {
        this.maxStockVolume = maxStockVolume;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(dateMaxStockVolume.getTime());
        out.writeLong(dateMinStockVolume.getTime());
        out.writeDouble(maxStockPriceClose);
        out.writeLong(maxStockVolume);
        
    }

    @Override
    public void readFields(DataInput in) throws IOException {

        try {

            dateMaxStockVolume = new Date(in.readLong());
            dateMinStockVolume = new Date(in.readLong());
            maxStockPriceClose = in.readDouble();
            maxStockVolume = in.readLong();
        }catch (EOFException e){

        }

    }

    @Override
    public String toString() {
        return "dateMaxStockVolume='" + frmt.format(dateMaxStockVolume) + '\'' +
                ", dateMinStockVolume='" + frmt.format(dateMinStockVolume) + '\'' +
                ", maxStockPriceClose=" + maxStockPriceClose;
    }
}
