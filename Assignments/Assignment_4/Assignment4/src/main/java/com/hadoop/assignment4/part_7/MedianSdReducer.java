package com.hadoop.assignment4.part_7;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianSdReducer extends Reducer<IntWritable, SortedMapWritable, IntWritable, MedianSdTuple>{
    
    private MedianSdTuple sd = new MedianSdTuple();
    private TreeMap<Double, Long> tm = new TreeMap<>();

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         double totalSum=0.0;
         double totalCount =0;
         
         for(SortedMapWritable v:values){
             
             for(Object e: v.entrySet()){
                 Map.Entry<WritableComparable, Writable> map = (Map.Entry<WritableComparable, Writable>) e;
              
                 double stockPrice = ((DoubleWritable)map.getKey()).get();
                 long stockCount = ((LongWritable)map.getValue()).get();
                 
                 totalCount += stockCount;
                 totalSum += stockCount*stockPrice;
                 
                 Long storedStockCount = tm.get(stockPrice);
                 
                 if(storedStockCount==null){
                     tm.put(stockPrice, stockCount);
                 }
                 else{
                     tm.put(stockPrice,stockCount+storedStockCount);
                 }
                       
             }
             v.clear();
             
         }
         
         double medIndex = totalCount/2;
         long preRating =0;
         long rating =0;
         double preKey = 0.0;
         
         for(Map.Entry<Double,Long> entry: tm.entrySet()){
             rating = preRating + entry.getValue();
             
             if(preRating<=medIndex && medIndex <rating){
                 if(totalCount %2 ==0 && preRating==medIndex){
                     sd.setMedian((entry.getKey()+preKey)/2);
                 }
                 else {
                     sd.setMedian(entry.getKey());
                 }
                 break;
             }
             preRating = rating;
             preKey= entry.getKey();
         }
         
         double mean = totalSum/totalCount;
         
         double sumOfSquares =0.0;
         
         sumOfSquares = tm.entrySet().stream().map((entry) -> (entry.getKey()-mean)*(entry.getKey()-mean)*entry.getValue()).reduce(sumOfSquares, (accumulator, _item) -> accumulator + _item);
         
         sd.setSd(Math.sqrt(sumOfSquares/(totalCount-1)));
         
         context.write(key, sd);
    }
    
    
}
