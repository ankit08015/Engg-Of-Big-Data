/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_2;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class
MinMaxStockReducer extends Reducer<Text, MinMaxStockTuple, Text, MinMaxStockTuple>{
    
     private MinMaxStockTuple outputTuple = new MinMaxStockTuple();

    @Override
    protected void reduce(Text key, Iterable<MinMaxStockTuple> values, Context context) throws IOException, InterruptedException {

        long maxStockVolume = Long.MIN_VALUE;
        Date maxDate = new Date();
        long minStockVolume = Long.MAX_VALUE;
        Date minDate = new Date();
        double maxStockPriceClose = Double.MIN_VALUE;
        
        for(MinMaxStockTuple v: values){

            if(v.getMaxStockVolume()>maxStockVolume){
                maxStockVolume= v.getMaxStockVolume();
                maxDate = v.getDateMaxStockVolume();
            }

           if(v.getMaxStockVolume()<minStockVolume){
                minStockVolume= v.getMaxStockVolume();
                minDate = v.getDateMaxStockVolume();
            }
           
             if(v.getMaxStockPriceClose()>maxStockPriceClose){
                maxStockPriceClose = v.getMaxStockPriceClose();
            }
             
             
        }
        
        outputTuple.setDateMaxStockVolume(maxDate);
        outputTuple.setDateMinStockVolume(minDate);
        outputTuple.setMaxStockPriceClose(maxStockPriceClose);
        outputTuple.setMaxStockVolume(maxStockVolume);
        
        context.write(key, outputTuple);
    }
    
    
}
