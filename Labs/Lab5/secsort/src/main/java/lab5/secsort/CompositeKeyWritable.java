package lab5.secsort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class CompositeKeyWritable implements WritableComparable<CompositeKeyWritable> {

	private String zip;
	private String bikeId;

	public CompositeKeyWritable() {
		super();
	}

	public CompositeKeyWritable(String zip, String id) {
		super();
		this.zip = zip;
		this.bikeId = id;
	}

	public void write(DataOutput out) throws IOException {

		out.writeUTF(zip);
		out.writeUTF(bikeId);
	}

	public void readFields(DataInput in) throws IOException {

		zip = in.readUTF();
		bikeId = in.readUTF();
	}

	public int compareTo(CompositeKeyWritable o) {
		int result = this.zip.compareTo(o.zip);
		if (result == 0) {
			return this.bikeId.compareTo(o.bikeId);
		}
		return result;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getBikeId() {
		return bikeId;
	}

	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}

	@Override
	public String toString() {

		return zip + "," + bikeId;
	}

}
