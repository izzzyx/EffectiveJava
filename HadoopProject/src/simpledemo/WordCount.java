package simpledemo;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.examples.WordCount.IntSumReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
	
	public static class TokenizMapper extends Mapper<Object, Text, Text, IntWritable>{
		
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
		public void mapper(Object key, Text value, Context context) throws IOException, InterruptedException{
			
			StringTokenizer tokenizer = new StringTokenizer(value.toString());
			if(tokenizer.hasMoreTokens()){
				word.set(tokenizer.nextToken());
				context.write(word, one);
			}
			
		}
		
	}
	
	public static class TokenizReducer extends Reducer<Text, Iterable<IntWritable>, Text, IntWritable>{
		
		private IntWritable counts = new IntWritable();
		
		public void reducer(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
			
			int sum = 0;
			
			for( IntWritable value : values ){
				
				sum += value.get();
				
			}
			
			counts.set(sum);
			
			context.write(key, counts);
			
			
		}
		 
	}
	
	public static void main(String args[]) throws IOException, InterruptedException, ClassNotFoundException{
		
		// 1.获得配置对象
		Configuration conf = new Configuration();
		
		// 2.根据配置对象创建job对象
		Job job = new Job(conf, "WordCount");
	
		// 3.对job对象进行各种设置
		// set a jar by finding where a given class came from
		job.setJarByClass(WordCount.class);
		job.setMapperClass(WordCount.TokenizMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(WordCount.TokenizReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// waitForCompletion()当job完成的时候返回true，可能主要的作用是监视job运行进度吧
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}
