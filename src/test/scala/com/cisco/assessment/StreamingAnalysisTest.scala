package com.cisco.assessment

import com.cisco.assessment.logic.StreamingAnalysis
import com.cisco.assessment.utils.Config
import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class StreamingAnalysisTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  var sparkSession: SparkSession = _
  var featureWithSchemaDf: DataFrame = _
  var rawJson = ""
  var testJson = ""
  var conf = new Config()

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
    testJson = "./src/test/resources/test.json"

    // Suppress INFO messages
    sparkSession.sparkContext.setLogLevel("WARN")
  }


  override def afterAll() {
    sparkSession.close()
    super.afterAll()
  }

  "A Dataframe" should "contain grouped results for Problem1" in  {

    val streamingLogDfs = sparkSession.read.format("json").load(testJson)
    streamingLogDfs.show

    conf.problemStatement = conf.PROBLEM_STATEMENT_1

    val analyzedDf = StreamingAnalysis.analyzeAggregatedDF(sparkSession, streamingLogDfs, conf)

    assert(analyzedDf.count() == 2)
    val row = analyzedDf.filter(col("uid") === "CFroyW1gkfoVUrbdEh").first()
    assert(row.get(1) === 3)
    assert(row.get(2) === 4.75)
    assert(row.get(3) === 8)

    analyzedDf.show

  }

  "A Dataframe" should "contain grouped results for Problem2" in  {

    val dfInput = sparkSession.read.format("json").load(testJson)
    val dfFiltered = dfInput.filter(dfInput("source_ip") === "10.61.156.51")
    dfFiltered.show

    conf.problemStatement = conf.PROBLEM_STATEMENT_2

    val analyzedDf = StreamingAnalysis.analyzeAggregatedDF(sparkSession, dfFiltered, conf)

    assert(analyzedDf.count() == 2)
    val row = analyzedDf.filter(col("domain") === "example.com").first()
    assert(row.get(2) === 2)
    assert(row.get(3) === 4)
    assert(row.get(4) === 2)
    assert(row.get(5) === 3.5)

    analyzedDf.show

  }

}
