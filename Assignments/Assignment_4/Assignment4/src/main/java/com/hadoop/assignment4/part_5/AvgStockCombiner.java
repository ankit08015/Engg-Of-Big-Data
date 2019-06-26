package com.hadoop.assignment4.part_5;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgStockCombiner extends Reducer<IntWritable, AvgStockPriceTuple, IntWritable, AvgStockPriceTuple>{

        AvgStockPriceTuple tuple = new AvgStockPriceTuple();
    @Override
    protected void reduce(IntWritable key, Iterable<AvgStockPriceTuple> values, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         double totalPrice = 0.0;
         long totalCount =0;
         
         for(AvgStockPriceTuple v: values){
             totalCount += v.getCount();
             totalPrice += v.getAvgStockPrice();
         }
         
         tuple.setAvgStockPrice(totalPrice);
         tuple.setCount(totalCount);
         
         context.write(key, tuple);
    }
    
    
}
