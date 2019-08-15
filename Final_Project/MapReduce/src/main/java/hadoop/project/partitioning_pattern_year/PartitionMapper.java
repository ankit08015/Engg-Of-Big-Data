package hadoop.project.partitioning_pattern_year;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PartitionMapper extends Mapper<Object, Text, IntWritable, Text> {


    private IntWritable outkey = new IntWritable();

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String [] tokens = value.toString().split(",");
        if(tokens[0].equals("Year"))
            return;

        StringBuffer sb = new StringBuffer();
        sb.append(tokens[0]);
        sb.append("\t");
        sb.append(tokens[8]);
        sb.append("\t");
        sb.append(tokens[16]);
        sb.append("\t");
        sb.append(tokens[17]);

        int year = Integer.parseInt(tokens[0]);

        outkey.set(year);

        // Write out the year with the input value
        context.write(outkey, new Text(sb.toString()));
    }
}