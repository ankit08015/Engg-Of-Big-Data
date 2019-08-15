package hadoop.project.rms_src_dest_carriers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RMSReducer extends Reducer<Text, RMSCountTuple, Text, RMSCountTuple> {

    private RMSCountTuple res= new RMSCountTuple();

    @Override
    protected void reduce(Text key, Iterable<RMSCountTuple> values, Context context) throws IOException, InterruptedException {

        int total=0;
        int arrDelay=0;
        int depDelay=0;

        for(RMSCountTuple tup : values){
            total +=tup.getTotalFlight();
            arrDelay +=tup.getArrDelay();
            depDelay +=tup.getDepDelay();
        }

        double avgArrDelay = (double)arrDelay/total;
        double avgDepDelay = (double)depDelay/total;

        double rms = Math.sqrt((avgArrDelay*avgArrDelay) + (avgDepDelay*avgDepDelay));

        res.setTotalFlight(total);
        res.setArrDelay(arrDelay);
        res.setDepDelay(depDelay);
        res.setRms(rms);



        context.write(key,res);
    }
}
