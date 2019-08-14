package hadoop.project.avg_distance_carrier;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class AverageMapper extends Mapper<Object, Text, Text, AverageCountTuple> {

    private AverageCountTuple tuple = new AverageCountTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))return;

        String carrier = tokens[8];
        int dist=0;
        int flightTime =0;

        try {
            dist = Integer.parseInt(tokens[18]);

            flightTime = Integer.parseInt(tokens[6]);
        } catch (Exception e){
            return;
        }
        tuple.setFlightCount(1);
        tuple.setDistCount(dist);
        tuple.setAirTime(flightTime);


        context.write(new Text(carrier),tuple);
    }
}
