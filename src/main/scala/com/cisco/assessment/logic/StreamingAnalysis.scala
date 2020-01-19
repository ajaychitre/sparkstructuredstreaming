package com.cisco.assessment.logic

import com.cisco.assessment.utils.Config
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

object StreamingAnalysis {

  def analyzeAggregatedDF(spark: SparkSession, df: DataFrame, config: Config) = {
    // add logic for handle input DF and analysis. Feel free to modify the functions as per requirement

    if (config.problemStatement.equals(config.PROBLEM_STATEMENT_1)) {
      df.groupBy(col("uid")).agg(
        approx_count_distinct(col("sub_domain")).alias("numberDistinctSubDomain"),
        avg(col("sub_domain_entropy")).alias("averageSubDomainEntropy"),
        count(col("uid")).alias("totalUdpRequest")
      )
    } else {
      df.groupBy(col("source_ip"), col("domain")).agg(
        approx_count_distinct(col("sub_domain")).alias("numberDistinctSubDomains"),
        count(col("uid")).alias("totalRequest"),
        sum(when(col("protocol") === "tcp", 1)
          .when(col("protocol").notEqual("tcp"), 0))
          .alias("totalTcpRequest"),
        avg(col("sub_domain_entropy")).alias("averageSubDomainEntropy")
      )
    }
  }

}
