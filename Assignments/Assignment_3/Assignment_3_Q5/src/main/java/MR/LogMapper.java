package MR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    //    hadoop datatype
    Text word = new Text();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String line = value.toString();

        String[] logs = line.split(" - - ");
        
        if(logs.length>0){
        String ipAddress = logs[0];

        word.set(ipAddress);
        context.write(word,one);

        }
        //super.map(key, value, context); //To change body of generated methods, choose Tools | Templates.
    }

}
