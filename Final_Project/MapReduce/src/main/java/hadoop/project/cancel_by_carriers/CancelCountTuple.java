package hadoop.project.cancel_by_carriers;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CancelCountTuple implements Writable {

    private int flightCount=0;
    private int cancelFlightCount=0;
    private double cancelPercent =0.0;

    public int getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public int getCancelFlightCount() {
        return cancelFlightCount;
    }

    public void setCancelFlightCount(int cancelFlightCount) {
        this.cancelFlightCount = cancelFlightCount;
    }

    public double getCancelPercent() {
        return cancelPercent;
    }

    public void setCancelPercent(double cancelPercent) {
        this.cancelPercent = cancelPercent;
    }

    @Override
    public String toString() {
        return  "flightCount=" + flightCount +
                ", delayedFlightCount=" + cancelFlightCount +
                ", delayPercent=" + String.format("%.2f", cancelPercent);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(flightCount);
        dataOutput.writeInt(cancelFlightCount);
        dataOutput.writeDouble(cancelPercent);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        flightCount = dataInput.readInt();
        cancelFlightCount = dataInput.readInt();
        cancelPercent = dataInput.readDouble();

    }
}
