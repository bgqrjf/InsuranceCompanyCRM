package com.sylg.bs.common.config.spark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.apache.spark.streaming.kafka010.LocationStrategy;
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

	@Autowired
	private JavaSparkContext sc;
	@Autowired
	private SparkConf sparkConf;

	/**
	 * 
	 * @Description: 统计文本每个单词的个数
	 * @author QSNP242
	 * @return Map<String,Object>
	 * @throws
	 * @date 2018年4月12日
	 */
	public Map<String, Object> sparkDemo() {

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

	/**
	 * 
	 * @Description: 计算消费者收到的消息长度
	 * @author QSNP242
	 * @param list
	 *            void
	 * @throws
	 * @date 2018年4月12日
	 */
	@KafkaListener(topics = { "demo", "sbKafka" })
	public void kafka(List<String> list) {
		// 并行化list
		JavaRDD<String> lines = sc.parallelize(list);

		// 定义lineLengths作为Map转换的结果 由于惰性，不会立即计算lineLengths
		JavaRDD<Integer> lineLengths = lines.map(s -> {
			System.out.println("收到的消息=======:" + s);
			return s.length();
		});
		int totalLength = lineLengths.reduce((x, y) -> {
			return x + y;
		});
		System.out.println("消息长度为==============:" + totalLength);
	}
	/*
	 * @KafkaListener(topics = {"demo","sbKafka"}) public void kafka(String
	 * message) { String brokers = "127.0.0.1:9092"; String topics =
	 * "demo,sbKafka"; Set<String> topicsSet = new
	 * HashSet<>(Arrays.asList(topics.split(","))); Map<String, Object>
	 * kafkaParams = new HashMap<>(); kafkaParams.put("metadata.broker.list",
	 * brokers); kafkaParams.put("bootstrap.servers", "127.0.0.1:9092");
	 * kafkaParams.put("key.serializer",
	 * "org.apache.kafka.common.serialization.StringSerializer");
	 * kafkaParams.put("value.serializer",
	 * "org.apache.kafka.cpmmon.serialization.StringSerializer");
	 * kafkaParams.put("key.deserializer",
	 * "org.apache.kafka.common.serialization.StringDeserializer");
	 * kafkaParams.put("value.deserializer",
	 * "org.apache.kafka.common.serialization.StringDeserializer");
	 * kafkaParams.put("group.id", "spark-streaming-test"); JavaStreamingContext
	 * jssc = new JavaStreamingContext(sparkConf, Durations.seconds(5));
	 * 
	 * 区别Receiver接收数据，这种方式定期地从kafka的topic+partition中查询最新的偏移量，
	 * 再根据偏移量范围在每个batch里面处理数据，使用的是kafka的简单消费者api 优点: A、
	 * 简化并行，不需要多个kafka输入流，该方法将会创建和kafka分区一样的rdd个数，而且会从kafka并行读取。
	 * B、高效，这种方式并不需要WAL，WAL模式需要对数据复制两次，第一次是被kafka复制，另一次是写到wal中
	 * C、恰好一次语义(Exactly-once-semantics)，传统的读取kafka数据是通过kafka高层次api把偏移量写入
	 * zookeeper中，存在数据丢失的可能性是zookeeper中和ssc的偏移量不一致。
	 * EOS通过实现kafka低层次api，偏移量仅仅被ssc保存在checkpoint中，消除了zk和ssc偏移量不一致的问题。
	 * 缺点是无法使用基于zookeeper的kafka监控工具
	 * 
	 * JavaInputDStream<ConsumerRecord<Object, Object>> inputDStream =
	 * KafkaUtils .createDirectStream(jssc,
	 * LocationStrategies.PreferConsistent(),
	 * ConsumerStrategies.Subscribe(topicsSet, kafkaParams)); //遍历 inputDStream
	 * .foreachRDD(new VoidFunction<JavaRDD<ConsumerRecord<Object, Object>>>() {
	 *//**
	 * @Fields serialVersionUID
	 */
	/*
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void call(JavaRDD<ConsumerRecord<Object, Object>> rdd)
	 * throws Exception { rdd.foreach(new VoidFunction<ConsumerRecord<Object,
	 * Object>>() {
	 *//**
	 * @Fields serialVersionUID
	 */
	/*
	 * private static final long serialVersionUID = 1L;
	 * 
	 * @Override public void call(ConsumerRecord<Object, Object> arg0) throws
	 * Exception { String topic = arg0.topic(); System.err.println("topic==" +
	 * topic); Object value = arg0.value(); System.err.println("value==" +
	 * value); }
	 * 
	 * }); }
	 * 
	 * }); }
	 */
}