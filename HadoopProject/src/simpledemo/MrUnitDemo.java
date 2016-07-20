package simpledemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

// 一个统计输入中第1个项的值为1的行的第4个项及其出现次数的MR
public class MrUnitDemo {
	
	@Before
	public void setUp(){
		SMSCDRMapper mapper = new SMSCDRMapper();
		SMSCDRReducer reducer = new SMSCDRReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}
	
	@Test
	public void testMapper() throws IOException{
		mapDriver.withInput(new LongWritable(), new Text(
				"655209;1;796764372490213;804422938115889;6"));
		mapDriver.withOutput(new Text("6"), new IntWritable(1));
		mapDriver.runTest();
	}
	
	@Test
	public void testReducer() throws IOException{
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("6"), values);
		reduceDriver.withOutput(new Text("6"), new IntWritable(2));
		reduceDriver.runTest();
	}
	
	@Test
	public void testMapReducer() throws IOException{
		mapReduceDriver.withInput(new LongWritable(), new Text(
	              "655209;1;796764372490213;804422938115889;6"));
	    List<IntWritable> values = new ArrayList<IntWritable>();
	    values.add(new IntWritable(1));
	    values.add(new IntWritable(1));
	    mapReduceDriver.withOutput(new Text("6"), new IntWritable(2));
	    mapReduceDriver.runTest();
	}
	
	/* 
	 * mapper class
	 * 从一个如下格式的输入行里以;分割获得第1个项的值为1的行的第4个项，并计数
	 * 655209;1;796764372490213;804422938115889;6 is the Sample record format
	 */
	public class SMSCDRMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		private Text status = new Text();
		private IntWritable addone = new IntWritable();
		
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException{
			String[] line = status.toString().split(";");
			if(Integer.valueOf(line[1]) == 1){
				status.set(line[4]);
				context.write(status, addone);
			}
		}
	}
	
	// reduce class
	public class SMSCDRReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
		
		protected void reducer(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
			int sum = 0;
			for(IntWritable value : values){
				sum += value.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}
	
	// variable
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	
	
}
