package hadoop.project.hier_pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SrcCarrierMapper extends Mapper<Object, Text, Text, Text> {
    private Text outkey = new Text();
    private Text outvalue = new Text();

    public void map(Object key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String[] tokens = value.toString().split("\t");

        // The foreign join key is the post ID
        outkey.set(tokens[0]);

        // Flag this record for the reducer and then output
        outvalue.set("C" + tokens[1]);
        context.write(outkey, outvalue);
    }
}