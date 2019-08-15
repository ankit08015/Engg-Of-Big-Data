package hadoop.project.rms_src_dest_carriers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class RMSMapper extends Mapper<Object, Text, Text, RMSCountTuple> {

    private RMSCountTuple tuple = new RMSCountTuple();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String [] tokens = value.toString().split(",");

        if(tokens[0].equals("Year"))return;

        String src = tokens[16];
        String dest = tokens[17];
        String carrier = tokens[8];

       int arrDelay=0;
       int depDelay=0;



        try {
            arrDelay = Integer.parseInt(tokens[14]);
            depDelay = Integer.parseInt(tokens[15]);
        }catch (Exception e){

        }

        String newKey = src+"-" + dest +"\t"+carrier;

        tuple.setArrDelay(arrDelay);
        tuple.setDepDelay(depDelay);
        tuple.setTotalFlight(1);

        context.write(new Text(newKey),tuple);
    }
}
