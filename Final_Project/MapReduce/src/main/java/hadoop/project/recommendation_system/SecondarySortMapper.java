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
         
         String [] stocks = value.toString().split(",");

         try {
             String stockSymbol = stocks[1];
             String date = stocks[2];

             CompositeKey coKey = new CompositeKey(stockSymbol, date);

             context.write(coKey, NullWritable.get());
         }catch(Exception e){
             e.getStackTrace();
         }
         
    }
    
    
}
