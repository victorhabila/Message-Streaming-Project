package com.MessageStreaming.MessageStreamingApp.service;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private CassandraService cassandraService;

    @KafkaListener(topics = "demo-topic", groupId = "my-group")
    public void consumeMessage(String message) {
        System.out.println("Consumed message: " + message);
        processMessageInSpark(message);
    }

    private void processMessageInSpark(String message) {
        SparkConf conf = new SparkConf()
                .setAppName("KafkaSparkIntegration")
                .setMaster("spark://spark-master:7077")
                .set("spark.metrics.conf", "none") // Disable Spark metrics
                .set("spark.ui.enabled", "false"); // Disable the Spark UI

        // Using try-with-resources to auto-close the SparkContext
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            // Split the message into words and process in Spark
            List<String> data = Arrays.asList(message.split(" "));
            JavaRDD<String> rdd = sc.parallelize(data);

            long wordCount = rdd.count();
            System.out.println("Processed word count in Spark: " + wordCount);

            // Save the message in Cassandra
            cassandraService.saveMessage(message);

        } catch (Exception e) {
            // Handle any exceptions during Spark processing
            System.err.println("Error processing message in Spark: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
