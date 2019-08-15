package hadoop.project.hier_pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<Object, Text, Text, Text> {

    private Text outkey = new Text();
    private Text outvalue = new Text();

    public void map(Object key, Text value, Mapper.Context context)
            throws IOException, InterruptedException {

        String[] tokens = value.toString().split(",");
        if (tokens[0].equals("iata"))
            return;

        // The foreign join key is the post ID
        outkey.set(tokens[0]);


        // Flag this record for the reducer and then output
        StringBuffer sb = new StringBuffer();
        sb.append(tokens[1]);
        sb.append(" ");
        sb.append(tokens[2]);
        sb.append(" ");
        sb.append(tokens[3]);
        sb.append(" ");
        sb.append(tokens[4]);

        outvalue.set("P" + sb.toString());
        context.write(outkey, outvalue);
    }
}