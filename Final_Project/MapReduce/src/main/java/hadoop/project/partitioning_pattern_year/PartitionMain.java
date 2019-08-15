package hadoop.project.partitioning_pattern_year;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class PartitionMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{


        Configuration conf = new Configuration();
        // Create a new Job
        Job job = Job.getInstance(conf,"wordcount");
        job.setJarByClass(PartitionMain.class);

        // Specify various job-specific parameters
        job.setJobName("myjob");


        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);


        job.setMapperClass(PartitionMapper.class);
        // Set custom partitioner and min last access date
        job.setPartitionerClass(PartitionPartitioner.class);
        PartitionPartitioner.setMinLastAccessDate(job, 1987);

// Last access dates span between 2008-2011, or 4 years
        job.setNumReduceTasks(22);

        job.setReducerClass(PartitionReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // Submit the job, then poll for progress until the job is complete
        System.exit(job.waitForCompletion(true)?0:1);

    }
}
