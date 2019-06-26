/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class MovieReducer extends Reducer<Text,RatingCustomWritable, Text, MovieTuple> {
    
    MovieTuple mt = new MovieTuple();
    
    ArrayList<Long> list = new ArrayList<>();

    @Override
    protected void reduce(Text key, Iterable<RatingCustomWritable> values, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         long sum= 0;
         long count =0;
         for(RatingCustomWritable v:values){
             long rating = (long) v.getRating();
             list.add(rating);
             sum += rating;
             count +=v.getCount();
             
         }
         
         double avg= sum/count;
         
         Collections.sort(list);
         
         double median =0.0;
         
         if(count%2==0){
             median= (list.get((int) (count/2))+list.get((int) (count/2-1)))/2;
         }
         else{
             median= list.get((int) (count/2));
         }
         
         double sumOfSquares=0.0;
         
         for(long r:list){
             sumOfSquares += (r-avg)*(r-avg);
         }
         
         double stdDev= Math.sqrt(sumOfSquares/(count-1));
         
         mt.setRatingMedian(median);
         mt.setRatingStdDev(stdDev);
         
         context.write(key, mt);
    }
    
    
    
}
