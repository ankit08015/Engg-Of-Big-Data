package lab5.secsort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, CompositeKeyWritable, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CompositeKeyWritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();

		String[] tokens = line.split(",");
		String zip = null;
		String bikeId = null;

		try {

			zip = tokens[10];
			bikeId = tokens[8];

		} catch (Exception e) {

		}

		if (zip != null && bikeId != null) {

			CompositeKeyWritable outKey = new CompositeKeyWritable(zip, bikeId);

			context.write(outKey, NullWritable.get());
			
		}
	}

}
