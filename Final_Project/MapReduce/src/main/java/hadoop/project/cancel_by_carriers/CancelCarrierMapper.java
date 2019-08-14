package hadoop.project.cancel_by_carriers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class CancelCarrierMapper extends Mapper<Object, Text, Text, CancelCountTuple> {

    private CancelCountTuple tuple = new CancelCountTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))return;

        String carrier = tokens[8];


        try {
            String can = tokens[21];
            tuple.setCancelFlightCount(Integer.parseInt(can));

        }catch (Exception e){
            tuple.setCancelFlightCount(0);
        }


        tuple.setFlightCount(1);

        context.write(new Text(carrier),tuple);
    }
}
