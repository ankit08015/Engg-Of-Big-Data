/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoop.assignment4.part_3;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author ankit
 */
public class StockMapper extends Mapper<Object, Text, Text, Text> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
         //To change body of generated methods, choose Tools | Templates.
         
        String[] stocks= value.toString().split(",");
        if(!stocks[0].equals("exchange") && stocks.length==9){
            String stockSymbol = stocks[1];
            
            String stockPriceClose = stocks[6];
            String stockVolume = stocks[7];
            String date = stocks[2];
            
            StringBuffer sb = new StringBuffer();
            
            sb.append(stockPriceClose);
            sb.append(",");
            sb.append(stockVolume);
            sb.append(",");
            sb.append(date);
            
            
            context.write(new Text(stockSymbol), new Text(sb.toString()));
            
            
        }
    }
    
}
