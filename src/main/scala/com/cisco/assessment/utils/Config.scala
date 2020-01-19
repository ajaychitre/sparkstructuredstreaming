package com.cisco.assessment.utils

// class for storing all configs
class Config {

  val appName: String = "sparkAssessment"
  val shufflePartitions: String = "50"
  val sqlBroadcastTimeout: String = "300"
  val bootstrapServer = "localhost:9092" // give your kafka broker here
  val startingOffset = "earliest" // offset maintenance
  val inputTopic = "cisco" //input kafka topic
  val outputTopic = "outputcisco" //output kafka topic
  val failOnDataLoss = false
  val maxOffsetsPerTrigger = 10000 // max offset per streaming trigger
  val checkpointLocation = "./checkpoint" // streaming checkpoint location
  val outputJobName = "myjob"
  val watermarkDelayThreshold: String = "1 minutes"
  val watermarkField = "@timestamp"
  val windowColumn: String = "@timestamp"
  val windowDuration: String = "1 minutes"
  val slideDuration: String = "1 minutes"

  val PROBLEM_STATEMENT_1 = "problem1"
  val PROBLEM_STATEMENT_2 = "problem2"

  // Change the following configuration depending on which problem you want to solve.
  var problemStatement = "problem2"

}
