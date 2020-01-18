package com.cisco.assessment.logic

import com.cisco.assessment.utils.Config
import org.apache.spark.sql.{DataFrame, SparkSession}

object StreamingAnalysis {

  def analyzeAggregatedDF(spark: SparkSession, df: DataFrame, config: Config) = {
    // add logic for handle input DF and analysis. Feel free to modify the functions as per requirement
  }

}
