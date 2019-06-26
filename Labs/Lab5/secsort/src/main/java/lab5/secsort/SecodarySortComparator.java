package lab5.secsort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecodarySortComparator extends WritableComparator {

	protected SecodarySortComparator() {
		super(CompositeKeyWritable.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {

		CompositeKeyWritable ckw1 = (CompositeKeyWritable) a;
		CompositeKeyWritable ckw2 = (CompositeKeyWritable) b;

		int result = ckw1.getZip().compareTo(ckw2.getZip());

		if (result == 0) {
			return -ckw1.getBikeId().compareTo(ckw2.getBikeId());
		}

		return result;
	}

	
	
}
