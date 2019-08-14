package hadoop.project.cancel_by_carriers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CancelCarrierReducer extends Reducer<Text, CancelCountTuple, Text, CancelCountTuple> {

    private CancelCountTuple res= new CancelCountTuple();

    @Override
    protected void reduce(Text key, Iterable<CancelCountTuple> values, Context context) throws IOException, InterruptedException {

        int total=0;
        int cancelTotal=0;

        for(CancelCountTuple dt: values){
            total += dt.getFlightCount();
            cancelTotal +=dt.getCancelFlightCount();
        }

        double percent = ((double)cancelTotal/total)*100;

        res.setCancelFlightCount(cancelTotal);
        res.setFlightCount(total);
        res.setCancelPercent(percent);

        context.write(key,res);
    }
}
