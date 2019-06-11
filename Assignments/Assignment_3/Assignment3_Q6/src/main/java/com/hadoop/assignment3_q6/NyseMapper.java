/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q6;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class NyseMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>{

    //    hadoop datatype
    Text word = new Text();
   

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] logs = line.split(",");
        if(logs.length >5){
        String stock = logs[1];
        double stock_price_high=0.0;
        try{
         stock_price_high= Double.parseDouble(logs[4]);
     
        }
        catch (Exception e){
        
        }
            DoubleWritable one = new DoubleWritable(stock_price_high);
             word.set(stock);
             context.write(word,one);
        }
    }

}
