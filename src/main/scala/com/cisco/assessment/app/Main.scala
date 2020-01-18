package com.cisco.assessment.app

import com.cisco.assessment.logic.{RtStreamingQueryManager, StreamingAnalysis}
import com.cisco.assessment.model.KafkaStream
import com.cisco.assessment.utils.{Config, SparkLogging}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

// This is the main initiation class
object Main extends SparkLogging {

  var conf = new Config()
  def main(args: Array[String]): Unit = {

    startSparkStreaming

  }

  def startSparkStreaming() {

    val sparkConf = new SparkConf(true)
      .setAppName(conf.appName)
      .set("spark.sql.broadcastTimeout", conf.sqlBroadcastTimeout)
      .set("spark.sql.shuffle.partitions", conf.shufflePartitions)
    val spark = SparkSession.builder
      .config(sparkConf)
      .getOrCreate()

    // Reading event stream from kafka source
    //val streamingLogDfs = KafkaStream.readKafkaStream(spark, conf)

    // parsing input data frame and aggregation logic
    // val analyzedDf = StreamingAnalysis.analyzeAggregatedDF(spark, streamingLogDfs, conf)

    // writing event stream to sink kafka
    //KafkaStream.writeStreamToKafka(spark, analyzedDf, conf)

    // Implementation for managing stream queries
    //RtStreamingQueryManager.manageStreamingQueries(spark, "StopRealTimeCallAnalysis", "RestartRealTimeCallAnalysis", startSparkStreaming)
  }

}
