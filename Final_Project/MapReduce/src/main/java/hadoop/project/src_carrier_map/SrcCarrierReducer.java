package hadoop.project.src_carrier_map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

public class SrcCarrierReducer extends Reducer<Text, Text, Text, Text> {

    // just like in mongoDB values is iterable
    private HashSet<String> carriers = new HashSet<>();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {


        for(Text v: values){
            carriers.add(v.toString());
            // can we use this--  Integer.parseInt(v.toString());
        }
        for(String car: carriers)
        context.write(key, new Text(car));

        //super.reduce(key, values, context); //To change body of generated methods, choose Tools | Templates.
    }

}