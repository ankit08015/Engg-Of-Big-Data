package hadoop.project.inverted_index_count;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class IndexMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] tokens= line.split("\t");
        String pair[] = tokens[0].split("-");
        String src = pair[0];
        String dest = pair[1];

        context.write(new Text(src+":"),new Text(dest));
    }


}
