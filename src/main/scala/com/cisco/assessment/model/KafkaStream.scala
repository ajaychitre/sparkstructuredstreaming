package com.cisco.assessment.model

import com.cisco.assessment.utils.{Config, DataSchema}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.types.{StringType, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Please note that I am reading from the input file and writing to a Kafka topic because the instructions said we
  * can do it either way.
  */
object KafkaStream {

  // sample logic to read streams from kafka
  def readKafkaStream(sparkSession: SparkSession, configProperties: Config): DataFrame = {
    sparkSession.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", configProperties.bootstrapServer)
      .option("startingOffsets", configProperties.startingOffset)
      .option("subscribe", configProperties.inputTopic)
      .option("failOnDataLoss", configProperties.failOnDataLoss)
      .option("maxOffsetsPerTrigger", configProperties.maxOffsetsPerTrigger)
      .format("json")
      .schema(DataSchema.uidSchema)
      .load("sample-data/input")
  }

  // sample logic to write stream result to kafka
  def writeStreamToKafka(sparkSession: SparkSession, df: DataFrame, configProperties: Config): StreamingQuery = {
    import sparkSession.implicits._
    val wr_df = df.select(to_json(struct($"*")).cast(StringType).alias("value"))
    val query = wr_df.writeStream
      .queryName(configProperties.outputJobName)
      .format("kafka")
      .option("kafka.bootstrap.servers", configProperties.bootstrapServer)
      .option("topic", configProperties.outputTopic)
      .option("checkpointLocation", configProperties.checkpointLocation)
      .outputMode("update")
      .start()
    query

  }

}
