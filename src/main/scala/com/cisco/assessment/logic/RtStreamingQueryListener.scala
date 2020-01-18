package com.cisco.assessment.logic
import org.apache.spark.sql.streaming.StreamingQueryListener

class RtStreamingQueryListener extends StreamingQueryListener {
  def onQueryStarted(event: StreamingQueryListener.QueryStartedEvent): Unit = {
    println(s"Query with name ${event.name}: ${event.id} started")
  }

  def onQueryProgress(event: StreamingQueryListener.QueryProgressEvent): Unit = {
    if (event.progress.numInputRows != 0) {
      val eventTime: java.util.Map[String, String] = event.progress.eventTime
      println(s"${event.progress.name}: Watermark: ${eventTime.getOrDefault("watermark", "")}, InputRows: ${event.progress.numInputRows}")
    }
  }

  def onQueryTerminated(event: StreamingQueryListener.QueryTerminatedEvent): Unit = {
    println(s"Query with id ${event.id} terminated")
  }
}
