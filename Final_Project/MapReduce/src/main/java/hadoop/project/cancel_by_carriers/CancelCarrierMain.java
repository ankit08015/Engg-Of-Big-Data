package hadoop.project.cancel_by_carriers;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class CancelCarrierMain {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException{


        Configuration conf = new Configuration();
        // Create a new Job
        Job job = Job.getInstance(conf,"wordcount");
        job.setJarByClass(CancelCarrierMain.class);

        // Specify various job-specific parameters
        job.setJobName("myjob");


        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(CancelCountTuple.class);


        job.setMapperClass(CancelCarrierMapper.class);
        job.setCombinerClass(CancelCarrierReducer.class);
        job.setReducerClass(CancelCarrierReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(CancelCountTuple.class);

        // Submit the job, then poll for progress until the job is complete
        System.exit(job.waitForCompletion(true)?0:1);

    }
}
