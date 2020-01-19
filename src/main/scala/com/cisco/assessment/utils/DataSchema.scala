package com.cisco.assessment.utils

import org.apache.spark.sql.types.StructType

object DataSchema {

  val uidSchema = new StructType()
    .add("uid", "string")
    .add("source_ip", "string")
    .add("destination_ip", "string")
    .add("protocol", "string")
    .add("domain", "string")
    .add("sub_domain", "string")
    .add("sub_domain_entropy", "string")

}
