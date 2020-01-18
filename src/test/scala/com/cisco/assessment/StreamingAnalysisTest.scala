package com.cisco.assessment

import org.apache.spark.sql._
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class StreamingAnalysisTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  var sparkSession: SparkSession = _
  var featureWithSchemaDf: DataFrame = _
  var rawJson = ""

  override def beforeAll() {
    super.beforeAll()
    sparkSession = SparkSession
      .builder()
      .config("spark.master", "local[*]")
      .config("spark.ui.showConsoleProgress", "false")
      .config("spark.sql.session.timeZone", "UTC")
      .config("spark.ui.enabled", false)
      .config("spark.sql.shuffle.partitions", "5")
      .getOrCreate()

    rawJson = "sample-data/input/web_conference.json"
  }

  override def afterAll() {
    super.afterAll()
  }

}
