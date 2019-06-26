package com.hadoop.assignment4.part_7;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MedianSdMapper extends Mapper<Object, Text, IntWritable, SortedMapWritable>{

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         try{
             
         String[] stocks= value.toString().split(",");
        if(!stocks[0].equals("exchange") && stocks.length==9){
            String stockDate = stocks[2];
            int year = Integer.parseInt(stockDate.split("-")[0]);
            double avgPrice = Double.parseDouble(stocks[8]);
         SortedMapWritable sm = new SortedMapWritable();
         sm.put(new DoubleWritable(avgPrice), new LongWritable(1));
            
            context.write(new IntWritable(year), sm);
        }
         }
         catch(Exception e){
             e.getStackTrace();
         }
    }
    
    
}
