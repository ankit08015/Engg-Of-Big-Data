/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.NLineInputFormat;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class NLineMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    
//    hadoop datatype
    Text word = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        
      
        
        String lines = value.toString();
        String[] lineArray = lines.split("\\r?\\n");
        
        for(String line:lineArray){
        String[] tokens= line.split(" ");
        
        for(String s:tokens){
            word.set(s);
            context.write(word,one);
        }
            
        }
          
    
        //super.map(key, value, context); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
