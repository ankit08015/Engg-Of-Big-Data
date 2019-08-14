package hadoop.project.avg_distance_carrier;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AverageCountTuple implements Writable {

    private int flightCount=0;
    private int distCount=0;
    private int airTime=0;
    private double averageDist =0.0;
    private double averageAirTime=0.0;

    public int getAirTime() {
        return airTime;
    }

    public void setAirTime(int airTime) {
        this.airTime = airTime;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public int getDistCount() {
        return distCount;
    }

    public void setDistCount(int distCount) {
        this.distCount = distCount;
    }

    public double getAverageDist() {
        return averageDist;
    }

    public void setAverageDist(double averageDist) {
        this.averageDist = averageDist;
    }

    public double getAverageAirTime() {
        return averageAirTime;
    }

    public void setAverageAirTime(double averageAirTime) {
        this.averageAirTime = averageAirTime;
    }

    @Override
    public String toString() {
        return "AverageCountTuple{" +
                "Total Flights=" + flightCount +
                ", Total Distance=" + distCount +
                ", Total Air Time=" + airTime +
                ", Average Distance=" + String.format("%.2f", averageDist) +
                ", Average Air Time=" + String.format("%.2f", averageAirTime)+
                '}';
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(flightCount);
        dataOutput.writeInt(distCount);
        dataOutput.writeInt(airTime);
        dataOutput.writeDouble(averageDist);
        dataOutput.writeDouble(averageAirTime);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {

        flightCount = dataInput.readInt();
        distCount = dataInput.readInt();
        airTime = dataInput.readInt();
        averageDist = dataInput.readDouble();
        averageAirTime = dataInput.readDouble();

    }
}
