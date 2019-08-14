package hadoop.project.delay_month;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class DelayMonthMapper extends Mapper<Object, Text, Text, DelayCountTuple> {

    private String [] days ={"","1-January","2-February","3-March","4-April","5-May","6June","7-July","8-August","9-September","10-October","11-November","12-December"};
    private DelayCountTuple tuple = new DelayCountTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))return;

        String month = days[Integer.parseInt(tokens[1])];


        try {
            int delay = Integer.parseInt(tokens[14]);


            if (delay > 15) {
                tuple.setDelayedFlightCount(1);
            } else {
                tuple.setDelayedFlightCount(0);
            }
        }catch (Exception e){
            tuple.setDelayedFlightCount(0);
        }

        tuple.setFlightCount(1);

        context.write(new Text(month),tuple);
    }
}
