/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.KeyValueTextInputFormat;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class KeyReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
                       
        String sum ="";
        for(Text v: values){
            sum += v.toString() + " ";
            // can we use this--  Integer.parseInt(v.toString());
        }
        
        context.write(key, new Text(sum)); 
    }
    
}
