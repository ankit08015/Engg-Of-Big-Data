/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.KeyValueTextInputFormat;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class KeyMapper extends Mapper<Text, Text, Text, Text> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
       // String val = value.toString();
        //IntWritable v = new IntWritable(Integer.parseInt(val));
        context.write(key,value); //To change body of generated methods, choose Tools | Templates.
    }
    

    
    
}
