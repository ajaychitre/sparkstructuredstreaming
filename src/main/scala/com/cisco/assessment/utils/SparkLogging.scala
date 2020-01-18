package com.cisco.assessment.utils

import org.apache.log4j

trait SparkLogging { // Make the log field transient so that objects with Logging can
  // be serialized and used on another machine
  @transient private val log_ = log4j.LogManager.getLogger(logName)

  // Method to get the logger name for this object
  protected def logName = {
    // Ignore trailing $'s in the class names for Scala objects
    this.getClass.getName.stripSuffix("$")
  }

  protected def log: log4j.Logger = {
    log_
  }
}
