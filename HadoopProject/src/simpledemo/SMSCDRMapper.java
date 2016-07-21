package simpledemo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;

/* 
 * mapper class
 * 从一个如下格式的输入行里以;分割获得第1个项的值为1的行的第4个项，并计数
 * 655209;1;796764372490213;804422938115889;6 is the Sample record format
 */
public class SMSCDRMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private IntWritable addone = new IntWritable(1);
	
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException{
		String[] line = value.toString().split(";");
		if(Integer.valueOf(line[1]) == 1){
			value.set(line[4]);
			context.write(value, addone);
		}
	}
}


