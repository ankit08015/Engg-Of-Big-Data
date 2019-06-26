package com.hadoop.assignment4.part_5;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgStockMapper extends Mapper<Object, Text, IntWritable, AvgStockPriceTuple> {

    AvgStockPriceTuple tuple = new AvgStockPriceTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
        String[] stocks= value.toString().split(",");
        if(!stocks[0].equals("exchange") && stocks.length==9){
            String stockDate = stocks[2];
            int year = Integer.parseInt(stockDate.split("-")[0]);
            double avgPrice = Double.parseDouble(stocks[8]);
            
            tuple.setAvgStockPrice(avgPrice);
            tuple.setCount(1);
            
            context.write(new IntWritable(year), tuple);
        }
    }
    
    

}
