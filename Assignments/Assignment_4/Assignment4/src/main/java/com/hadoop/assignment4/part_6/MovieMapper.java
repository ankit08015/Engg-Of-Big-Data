/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_6;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class MovieMapper extends Mapper<LongWritable, Text, Text, RatingCustomWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         String [] rating = value.toString().split("::");
         
         String movie = rating[1];
         long rate = Long.parseLong(rating[2]);
         RatingCustomWritable rt = new RatingCustomWritable();
         rt.setRating(rate);
         rt.setCount(1);
         
         context.write(new Text(movie), rt);
         
    }
    
    
}
