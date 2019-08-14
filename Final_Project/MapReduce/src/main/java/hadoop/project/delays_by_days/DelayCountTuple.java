package hadoop.project.delays_by_days;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DelayCountTuple implements Writable {

    private int flightCount=0;
    private int delayedFlightCount=0;
    private double delayPercent =0.0;

    public int getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public int getDelayedFlightCount() {
        return delayedFlightCount;
    }

    public void setDelayedFlightCount(int delayedFlightCount) {
        this.delayedFlightCount = delayedFlightCount;
    }

    public double getDelayPercent() {
        return delayPercent;
    }

    public void setDelayPercent(double delayPercent) {
        this.delayPercent = delayPercent;
    }

    @Override
    public String toString() {
        return  "flightCount=" + flightCount +
                ", delayedFlightCount=" + delayedFlightCount +
                ", delayPercent=" + String.format("%.2f", delayPercent);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(flightCount);
        dataOutput.writeInt(delayedFlightCount);
        dataOutput.writeDouble(delayPercent);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        flightCount = dataInput.readInt();
        delayedFlightCount = dataInput.readInt();
        delayPercent = dataInput.readDouble();

    }
}
