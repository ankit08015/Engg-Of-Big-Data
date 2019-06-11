/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.textInputFormat;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class TextReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
                
        int sum=0;
        for(IntWritable v: values){
            sum += v.get();
            // can we use this--  Integer.parseInt(v.toString());
        }
        
        context.write(key, new IntWritable(sum)); 
    }


    
    
}
