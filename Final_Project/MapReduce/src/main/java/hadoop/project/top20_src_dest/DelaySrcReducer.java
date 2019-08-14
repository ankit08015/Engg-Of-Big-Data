package hadoop.project.top20_src_dest;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class DelaySrcReducer extends Reducer<NullWritable, Text, NullWritable, Text> {
    private TreeMap<Double, Text> countMap = new TreeMap<>();

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {

            String[] tokens = value.toString().split("\t");
            double perc = Double.parseDouble(tokens[1]);

            countMap.put(perc, new Text(value));


            if (countMap.size() > 20)
                countMap.remove(countMap.lastKey());
        }

        for (Text t : countMap.values())
            context.write(NullWritable.get(), t);
    }
}
