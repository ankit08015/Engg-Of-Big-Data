package com.hadoop.assignment4.part_7;

import java.io.IOException;
import java.util.Map;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianSdCombiner extends Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         SortedMapWritable sm = new SortedMapWritable();
         
         for(SortedMapWritable v: values){
             for(Object e: v.entrySet()){
                 Map.Entry<WritableComparable, Writable> map = (Map.Entry<WritableComparable, Writable>) e;
                 
                 LongWritable count = (LongWritable) map.getValue();
                 DoubleWritable stockPrice = (DoubleWritable) map.getKey();
                 
                 LongWritable prevCount = (LongWritable) sm.get(stockPrice);
                 
                 if(prevCount==null){
                     sm.put(stockPrice, count);
                 }
                 else {
                     sm.put(stockPrice, new LongWritable(count.get()+prevCount.get()));
                 }
             }
             
             v.clear();
         }
         
         context.write(key, sm);
    }
    
    
}
