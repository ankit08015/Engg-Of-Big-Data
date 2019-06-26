/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_2;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class MinMaxStockMapper extends Mapper<Object, Text, Text, MinMaxStockTuple> {
    
    private MinMaxStockTuple outputTuple = new MinMaxStockTuple();
    private final static SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        
        String[] stocks= value.toString().split(",");
        if(!stocks[0].equals("exchange") && stocks.length==9){

            String stockSymbol = stocks[1];
            double stockPriceClose = Double.parseDouble(stocks[6]);
            long stockVolume = Long.parseLong(stocks[7]);


            try {
                outputTuple.setDateMaxStockVolume(frmt.parse(stocks[2]));
                outputTuple.setDateMinStockVolume(frmt.parse(stocks[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            outputTuple.setMaxStockPriceClose(stockPriceClose);
            outputTuple.setMaxStockVolume(stockVolume);

        
        
            context.write(new Text(stockSymbol), outputTuple);
        }

    }
    
    
}
