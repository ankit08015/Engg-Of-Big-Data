package lab5.secsort;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException
    {
        Job job = Job.getInstance();
        
        job.setJarByClass(Driver.class);
        
        job.setGroupingComparatorClass(GroupComparator.class);
        job.setSortComparatorClass(SecodarySortComparator.class);
        job.setPartitionerClass(KeyPartition.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        Path outDir = new Path(args[1]);
        FileOutputFormat.setOutputPath(job, outDir);
        
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        
        job.setNumReduceTasks(1);
        
        job.setOutputKeyClass(CompositeKeyWritable.class);
        job.setOutputValueClass(NullWritable.class);
        
        FileSystem fs = FileSystem.get(job.getConfiguration());
        if(fs.exists(outDir)) {
        	fs.delete(outDir, true);
        }
        
        job.waitForCompletion(true);
    }
}
