/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hadoop.project.recommendation_system;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 * @author ankit
 */
public class SecondarySortMapper extends Mapper<LongWritable, Text, CompositeKey, NullWritable>{

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
         String [] tokens = value.toString().split("\t",2);

         try {
             String srcDest = tokens[0];
             String carrInfo = tokens[1];

             CompositeKey coKey = new CompositeKey(srcDest, carrInfo);

             context.write(coKey, NullWritable.get());
         }catch(Exception e){
             e.getStackTrace();
         }
         
    }
    
    
}
