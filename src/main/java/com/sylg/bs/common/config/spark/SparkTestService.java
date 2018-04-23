package com.sylg.bs.common.config.spark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import scala.Tuple2;

/**
 * 
 * ClassName: SparkTestService
 * 
 * @Description:
 * @author QSNP242
 * @date 2018年4月11日
 */
@Service
public class SparkTestService {
	private static final Pattern SPACE = Pattern.compile(" ");

	/*@Autowired
	private JavaSparkContext sc;*/
	@Autowired
	private SparkConf sparkConf;
     @Autowired
     private JavaStreamingContext jssc;
	/**
	 * 
	 * @Description: 统计文本每个单词的个数
	 * @author QSNP242
	 * @return Map<String,Object>
	 * @throws
	 * @date 2018年4月12日
	 */
	/*public Map<String, Object> sparkDemo() {
		Map<String, Object> result = new HashMap<String, Object>();
		JavaRDD<String> lines = sc
				.textFile(
						"D:/Users/xlj/workspace/boot/src/main/resources/static/spark.txt")
				.cache();
		lines.map(str -> {
			System.out.println(str);
			return str;
		});
		System.out.println();
		System.out
				.println("-------------------------------------------------------");
		System.out.println(lines.count());

		JavaRDD<String> words = lines.flatMap(str -> Arrays.asList(
				SPACE.split(str)).iterator());

		JavaPairRDD<String, Integer> ones = words
				.mapToPair(str -> new Tuple2<String, Integer>(str, 1));

		JavaPairRDD<String, Integer> counts = ones.reduceByKey((Integer i1,
				Integer i2) -> (i1 + i2));

		JavaPairRDD<Integer, String> temp = counts
				.mapToPair(tuple -> new Tuple2<Integer, String>(tuple._2,
						tuple._1));

		JavaPairRDD<String, Integer> sorted = temp.sortByKey(false).mapToPair(
				tuple -> new Tuple2<String, Integer>(tuple._2, tuple._1));

		System.out.println();
		System.out
				.println("-------------------------------------------------------");
		System.out.println(sorted.count());
		List<Tuple2<String, Integer>> output = sorted.collect();
		for (Tuple2<String, Integer> tuple : output) {
			result.put(tuple._1(), tuple._2());
		}
		return result;
	}
*/
	

	
}