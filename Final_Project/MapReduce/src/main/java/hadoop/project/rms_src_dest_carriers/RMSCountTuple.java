package hadoop.project.rms_src_dest_carriers;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RMSCountTuple implements Writable {

    private int arrDelay=0;
    private int depDelay=0;
    private int totalFlight=0;
    private double rms  =0.0;

    public int getArrDelay() {
        return arrDelay;
    }

    public void setArrDelay(int arrDelay) {
        this.arrDelay = arrDelay;
    }

    public int getDepDelay() {
        return depDelay;
    }

    public void setDepDelay(int depDelay) {
        this.depDelay = depDelay;
    }

    public int getTotalFlight() {
        return totalFlight;
    }

    public void setTotalFlight(int totalFlight) {
        this.totalFlight = totalFlight;
    }

    public double getRms() {
        return rms;
    }

    public void setRms(double rms) {
        this.rms = rms;
    }

    @Override
    public String toString() {
        return "{" +
                "arrDelay=" + arrDelay +
                ", depDelay=" + depDelay +
                ", totalFlight=" + totalFlight +
                ", rms=" +  String.format("%.4f", rms)+
                '}';
    }

//
//    @Override
//    public String toString() {
//        return String.format("%.4f", rms);
//    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(arrDelay);
        dataOutput.writeInt(depDelay);
        dataOutput.writeInt(totalFlight);
        dataOutput.writeDouble(rms);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        arrDelay = dataInput.readInt();
        depDelay = dataInput.readInt();
        totalFlight = dataInput.readInt();
        rms = dataInput.readDouble();

    }
}
