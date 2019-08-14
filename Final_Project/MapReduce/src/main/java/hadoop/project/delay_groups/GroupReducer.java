package hadoop.project.delay_groups;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class GroupReducer extends Reducer<Text, IntWritable, Text, Text> {

    // just like in mongoDB values is iterable
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum=0;
        for(IntWritable v: values){
            sum += v.get();
            // can we use this--  Integer.parseInt(v.toString());
        }

        double total = 123534970.0;
        //double total = 1311827;

        double percent= (sum/total)*100;

        String res = String.format("%.2f", percent);

        context.write(key, new Text(res));

        //super.reduce(key, values, context); //To change body of generated methods, choose Tools | Templates.
    }

}