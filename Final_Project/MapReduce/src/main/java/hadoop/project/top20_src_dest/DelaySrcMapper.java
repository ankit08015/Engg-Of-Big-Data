package hadoop.project.top20_src_dest;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class DelaySrcMapper extends Mapper<Object, Text, NullWritable,Text> {


    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        context.write(NullWritable.get(),value);
    }
}
