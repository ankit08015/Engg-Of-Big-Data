package hadoop.project.top30_busy_src_dest_pair;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.TreeMap;

public class TopNReducer extends Reducer<NullWritable, Text, NullWritable, Text> {
    private TreeMap<Integer, Text> countMap = new TreeMap<>();

    @Override
    protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text value: values){
            String[] val= value.toString().split("\t");

            if(val.length==2){
                String pair = val[0];
                int count = Integer.parseInt(val[1]);
                countMap.put(count,new Text(value));
            }

            if(countMap.size()>30)
                countMap.remove(countMap.firstKey());
        }

        for(Text t: countMap.descendingMap().values())
            context.write(NullWritable.get(),t);
    }
}
