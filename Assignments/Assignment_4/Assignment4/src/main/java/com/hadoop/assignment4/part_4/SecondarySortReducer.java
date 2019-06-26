/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_4;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author ankit
 */
public class SecondarySortReducer extends Reducer<CompositeKey, NullWritable, CompositeKey, NullWritable>{

    @Override
    protected void reduce(CompositeKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         for(NullWritable v:values){
             context.write(key, v);
         }
    }
    
}
