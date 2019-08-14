package hadoop.project.delay_groups;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GroupMapper  extends Mapper<Object, Text, Text, IntWritable> {


    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))return;

        int delay =0;

        try {
            delay = Integer.parseInt(tokens[15]);

        }catch (Exception e){
           delay=0;
        }

        String grpKey="";

        if(delay<15)
            grpKey="Less than 15 Minutes";
        else if(delay>=15 && delay <=30)
            grpKey="Between 15 abd 30 minutes";
        else if(delay>30 && delay<60)
            grpKey="Between 30 minutes and 1 hour";
        else if(delay>=60 && delay<=120)
            grpKey="Between 1 hour and 2 hour";
        else
            grpKey="More than 2 hours";

        context.write(new Text(grpKey),new IntWritable(1));
    }
}
