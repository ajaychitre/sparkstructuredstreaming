package com.cisco.assessment.utils

// class for storing all configs
class Config {

  val appName: String = "sparkAssessment"
  val shufflePartitions: String = "50"
  val sqlBroadcastTimeout: String = "300"
  val bootstrapServer = "" // give your kafka broker here
  val startingOffset = "earliest" // offset maintenance
  val inputTopic = "" //input kafka topic
  val outputTopic = "" //output kafka topic
  val failOnDataLoss = false
  val maxOffsetsPerTrigger = 10000 // max offset per streaming trigger
  val checkpointLocation = "" // streaming checkpoint location
  val outputJobName = ""
  val watermarkDelayThreshold: String = "1 minutes"
  val watermarkField = "@timestamp"
  val windowColumn: String = "@timestamp"
  val windowDuration: String = "1 minutes"
  val slideDuration: String = "1 minutes"

}
