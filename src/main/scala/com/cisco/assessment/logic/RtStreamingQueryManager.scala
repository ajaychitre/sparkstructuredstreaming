package com.cisco.assessment.logic

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.SparkSession

import scala.util.{Failure, Success, Try}

object RtStreamingQueryManager {
  def manageStreamingQueries(spark: SparkSession, shutdownMarker: String, restartMarker: String, restartQueries: () => Unit): Unit = {

    val sparkQueryListener = new RtStreamingQueryListener()
    spark.streams.addListener(sparkQueryListener)

    val timeoutInMilliSeconds = 100000
    while (!spark.streams.active.isEmpty) {
      Try(spark.streams.awaitAnyTermination(timeoutInMilliSeconds)) match {
        case Success(result) => {
          if (result) {
            println("A streaming query was terminated successfully")
            spark.streams.resetTerminated()
          }
        }
        case Failure(e) => {
          println("Query failed with message: " + e.getMessage)
          e.printStackTrace()
          spark.streams.resetTerminated()
        }
      }

      if (checkMarker(shutdownMarker)) {
        spark.streams.active.foreach(query => {
          println(s"Stopping streaming query: ${query.name}")
          query.stop()
        })
        spark.stop()
        removeMarker(shutdownMarker)
      }

      if (checkMarker(restartMarker)) {
        spark.streams.active.foreach(query => {
          query.stop()
        })
        removeMarker(restartMarker)
        restartQueries()
      }
    }
    assert(spark.streams.active.isEmpty)
    spark.streams.removeListener(sparkQueryListener)
  }

  def checkMarker(markerFile: String): Boolean = {
    val fs = FileSystem.get(new Configuration())
    fs.exists(new Path("/tmp/" + markerFile))
  }

  def removeMarker(markerFile: String): Unit = {
    val fs = FileSystem.get(new Configuration())
    fs.delete(new Path("/tmp/" + markerFile), true)
  }
}
