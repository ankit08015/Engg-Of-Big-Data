/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_3;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class StockReducer extends Reducer<Text, Text, Text, Text>{

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


            long maxStockVolumeLoc = Long.parseLong(stocks[1]);
            String maxDateLoc = stocks[2];

            long minStockVolumeLoc = Long.parseLong(stocks[3]);
            String minDateLoc = stocks[4];


            double priceClose = Double.parseDouble(stocks[0]);
                    
            
            if(maxStockVolumeLoc>maxStockVolume){
                maxStockVolume= maxStockVolumeLoc;
                maxDate = maxDateLoc;
            }

           if(minStockVolumeLoc<minStockVolume){
                minStockVolume= minStockVolumeLoc;
                minDate = minDateLoc;
            }
           
             if(priceClose>maxStockPriceClose){
                maxStockPriceClose = priceClose;
            }
        }



        
        
        String value= "dateMaxStockVolume:'" + maxDate + '\'' +
                ", dateMinStockVolume:'" + minDate + '\'' +
                ", maxStockPriceClose:" + maxStockPriceClose  + '\'' ;
        
        
        context.write(key,new Text(value));
         
    }
    
}
