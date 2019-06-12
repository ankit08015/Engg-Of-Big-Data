/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment3_q7.KeyValueTextInputFormat;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 *
 * @author ankit
 */
public class KeyMainMR {
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{
        
        
    Configuration conf = new Configuration();
         // Create a new Job
     Job job = Job.getInstance(conf,"KeyValueCount");
     job.setJarByClass(KeyMainMR.class);
     
     // Specify various job-specific parameters     
     job.setJobName("myjob");
     
     
     FileInputFormat.addInputPath(job, new Path(args[0]));
     FileOutputFormat.setOutputPath(job, new Path(args[1]));
     
     
     job.setInputFormatClass(KeyValueTextInputFormat.class);
     job.setOutputFormatClass(TextOutputFormat.class);
     
     job.setMapOutputKeyClass(Text.class);
     job.setMapOutputValueClass(IntWritable.class);
     
     
     
     
     job.setMapperClass(KeyMapper.class);
     job.setReducerClass(KeyReducer.class);
     
     job.setOutputKeyClass(Text.class);
     job.setOutputValueClass(IntWritable.class);

     // Submit the job, then poll for progress until the job is complete
     System.exit(job.waitForCompletion(true)?0:1);
     
    }
    
}