package hadoop.project.avg_distance_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, AverageCountTuple, Text, AverageCountTuple> {

private AverageCountTuple tuple = new AverageCountTuple();

@Override
protected void reduce(Text key, Iterable<AverageCountTuple> values, Context context) throws IOException, InterruptedException {

        int totalFlight=0;
        int totalDist=0;
        int totalAirTime =0;



        for(AverageCountTuple dt: values){
        totalFlight += dt.getFlightCount();
        totalDist += dt.getDistCount();
        totalAirTime += dt.getAirTime();

        }

        double avgDist = (double)totalDist/totalFlight;
        double avgAirTime = (double)totalAirTime/totalFlight;

        tuple.setAirTime(totalAirTime);
        tuple.setDistCount(totalDist);
        tuple.setFlightCount(totalFlight);
        tuple.setAverageDist(avgDist);
        tuple.setAverageAirTime(avgAirTime);

        context.write(key,tuple);
        }
        }
