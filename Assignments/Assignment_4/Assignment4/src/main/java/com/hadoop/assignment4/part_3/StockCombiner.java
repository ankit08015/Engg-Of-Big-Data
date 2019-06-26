/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 * @author ankit
 */
public class StockCombiner extends Reducer<Text, Text, Text, Text> {

@Override
protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //To change body of generated methods, choose Tools | Templates.
        long maxStockVolume = Long.MIN_VALUE;
        String maxDate = null;
        long minStockVolume = Long.MAX_VALUE;
        String minDate = null;
        double maxStockPriceClose = Double.MIN_VALUE;

        for(Text v: values){
        String [] stocks = v.toString().split(",");
        long stockVolume = Long.parseLong(stocks[1]);
        String date = stocks[2];
        double priceClose = Double.parseDouble(stocks[0]);


        if(stockVolume>maxStockVolume){
        maxStockVolume= stockVolume;
        maxDate = date;
        }

        if(stockVolume<minStockVolume){
        minStockVolume= stockVolume;
        minDate = date;
        }

        if(priceClose>maxStockPriceClose){
        maxStockPriceClose = priceClose;
        }
        }





        StringBuffer sb = new StringBuffer();

        sb.append(maxStockPriceClose);
        sb.append(",");
        sb.append(maxStockVolume);
        sb.append(",");
        sb.append(maxDate);
        sb.append(",");
        sb.append(minStockVolume);
        sb.append(",");
        sb.append(minDate);



        context.write(key,new Text(sb.toString()));

        }

        }