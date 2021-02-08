package com.dataplatform.executors.flink.kafka.serde

import com.google.gson._

import java.sql.Timestamp
import java.time.LocalDateTime

private[serde] trait GsonProvider {

  @transient lazy val gson: Gson = {
    val builder = new GsonBuilder()
      .registerTypeAdapter(classOf[LocalDateTime], new LocalDateTimeAdapter)
      .registerTypeAdapter(classOf[Timestamp], new TimestampAdapter)

    builder.create()
  }
}