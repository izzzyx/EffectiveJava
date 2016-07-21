package simpledemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

//reduce class
public class SMSCDRReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	
	protected void reducer(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable value : values){
			sum += value.get();
		}
		context.write(key, new IntWritable(sum));
	}
}
