package hadoop.project.src_carrier_map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SrcCarrierMapper extends Mapper<LongWritable, Text, Text, Text> {

    //    hadoop datatype
    Text word = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] tokens= line.split(",");
        if(tokens[0].equals("Year"))return;
        String orig = tokens[16];
        String carrier = tokens[8];
        word.set(orig);
        context.write(word,new Text(carrier));
    }


}
