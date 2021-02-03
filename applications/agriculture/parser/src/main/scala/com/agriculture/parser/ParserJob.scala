package com.agriculture.parser

import com.agriculture.domain.TractorTelemetry
import com.iotplatform.executors.flink.FlinkJob
import com.iotplatform.executors.flink.kafka.Kafka
import org.apache.flink.streaming.api.scala._

final class ParserJob extends FlinkJob {
  override def script(): Unit = {
    val kafka = Kafka(
      servers = "localhost:9092",
      groupId = name
    )

    val telemetry: DataStream[TractorTelemetry] = kafka.readTopic[TractorTelemetry]("tractor_telemetry")

    val location = telemetry.map(r => r.location)

    location.print()
//
//    val fuel = telemetry.map(r => r.fuel)
  }
}

object ParserJob {
  def apply(): ParserJob = new ParserJob()
}