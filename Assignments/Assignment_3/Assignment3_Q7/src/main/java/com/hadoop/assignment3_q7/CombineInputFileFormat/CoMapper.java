/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.CombineInputFileFormat;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class CoMapper extends Mapper<FileLineWritable, Text, Text, IntWritable>{
    private Text txt = new Text();
    private IntWritable count = new IntWritable(1);
    public void map (FileLineWritable key, Text val, Context context) throws IOException, InterruptedException{
      StringTokenizer st = new StringTokenizer(val.toString());
        while (st.hasMoreTokens()){
          txt.set(st.nextToken());          
          context.write(txt, count);
        }
    }
}