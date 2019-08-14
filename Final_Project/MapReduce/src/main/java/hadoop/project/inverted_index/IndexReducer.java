package hadoop.project.inverted_index;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

public class IndexReducer extends Reducer<Text, Text, Text, Text> {

    // just like in mongoDB values is iterable
    HashSet<String> hs = new HashSet<>();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        hs.clear();
        StringBuffer sb = new StringBuffer("");
        for(Text v: values){
            hs.add(v.toString());
            // can we use this--  Integer.parseInt(v.toString());
        }

        for(String v: hs){
            sb.append(v);
            sb.append(" ");
        }

        context.write(key, new Text(sb.toString()));

        //super.reduce(key, values, context); //To change body of generated methods, choose Tools | Templates.
    }

}