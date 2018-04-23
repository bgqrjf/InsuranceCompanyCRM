package com.sylg.bs.common.config.kafka;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
/**
 * 
 * ClassName: Consumer 
 * @Description: 消费者
 * @author QSNP242
 * @date 2018年4月8日
 */

@Component
@Slf4j
public class Consumer {
	@Autowired
    private JavaStreamingContext jssc;
	private Logger log = Logger.getLogger(Consumer.class);
	 /**
     * 监听demo主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"demo","sbKafka"})
    public void consumer(String message){
    	log.info("message========   "+message);
    	System.err.println(message);
    }
    /**
     * 
     * @Description: spark streaming+kafka消费 
     * @author QSNP242
     * @param message  void
     * @throws InterruptedException 
     * @throws
     * @date 2018年4月13日
     */
    @KafkaListener(topics = {"demo","sbKafka"}) public void kafka(String
    		  message) throws InterruptedException { 
    			System.out.println("进入:"+message);
    			String brokers = "127.0.0.1:9092"; 
    		  String topics = "demo,sbKafka"; 
    		  Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(","))); 
    		  Map<String, Object> kafkaParams = new HashMap<>(); 
    		  kafkaParams.put("metadata.broker.list",brokers); 
    		  kafkaParams.put("bootstrap.servers", "127.0.0.1:9092");
    		  kafkaParams.put("key.serializer",
    		  "org.apache.kafka.common.serialization.StringSerializer");
    		  kafkaParams.put("value.serializer",
    		  "org.apache.kafka.cpmmon.serialization.StringSerializer");
    		  kafkaParams.put("key.deserializer",
    		  "org.apache.kafka.common.serialization.StringDeserializer");
    		  kafkaParams.put("value.deserializer",
    		  "org.apache.kafka.common.serialization.StringDeserializer");
    		  kafkaParams.put("group.id", "spark-streaming-test"); 
    		 
    		  
    		 /* 区别Receiver接收数据，这种方式定期地从kafka的topic+partition中查询最新的偏移量，
    		  再根据偏移量范围在每个batch里面处理数据，使用的是kafka的简单消费者api 
    		  优点: 
    		  A、 简化并行，不需要多个kafka输入流，该方法将会创建和kafka分区一样的rdd个数，而且会从kafka并行读取。
    		  B、高效，这种方式并不需要WAL，WAL模式需要对数据复制两次，第一次是被kafka复制，另一次是写到wal中
    		  C、恰好一次语义(Exactly-once-semantics)，传统的读取kafka数据是通过kafka高层次api把偏移量写入
    		  zookeeper中，存在数据丢失的可能性是zookeeper中和ssc的偏移量不一致。
    		  EOS通过实现kafka低层次api，偏移量仅仅被ssc保存在checkpoint中，消除了zk和ssc偏移量不一致的问题。
    		  缺点是无法使用基于zookeeper的kafka监控工具*/
    		 
    		  JavaInputDStream<ConsumerRecord<Object, Object>> inputDStream =
    		  KafkaUtils .createDirectStream(jssc,
    		  LocationStrategies.PreferConsistent(),
    		  ConsumerStrategies.Subscribe(topicsSet, kafkaParams)); 
    		  //遍历 inputDStream
    		  inputDStream.foreachRDD(x->{
    				  x.foreach(y->{
    					  System.out.println(y.topic());
    					  System.out.println(y.value().toString());
    					  System.out.println(y.offset());
    				  });
    			  
    		  });
    		  jssc.start();
    		  //awaitTermination方法等待流计算完成（手动或由于任何错误），来防止应用退出
    		  /*
    		   * streamingContext类的start()函数开始这个流式应用的运行，开始运行后，start()函数返回。
    		   * 调用awaitTermination()，driver将阻塞在这里，直到流式应用意外退出。
    		   * 另外，通过调用stop()函数可以优雅退出流式应用，通过将传入的stopSparkContext参数设置为false，
    		   * 可以只停止StreamingContext而不停止SparkContext（目前不知道这样做的目的）。
    		   * 流式应用退出后，不可以通过调用start()函数再次启动。
    		   */
   		     jssc.awaitTermination();
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
	/*@KafkaListener(topics = { "demo", "sbKafka" })*/
	/*public void kafka(List<String> list) {
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
	}*/
}
