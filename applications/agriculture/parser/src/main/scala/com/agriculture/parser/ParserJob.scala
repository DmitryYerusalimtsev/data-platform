package com.agriculture.parser

import com.agriculture.domain.TractorTelemetry
import com.iotplatform.executors.flink.FlinkJob
import com.iotplatform.executors.flink.kafka.Kafka
import org.apache.flink.streaming.api.scala._

final class ParserJob extends FlinkJob[ParserConfig] {

  override def script(): Unit = {
    val kafka = Kafka(config.kafka)

    val telemetry: DataStream[TractorTelemetry] = kafka.readTopic[TractorTelemetry]("tractor_telemetry")

    val location = telemetry.map(_.location)
    val fuel = telemetry.map(_.fuel)

    kafka.writeToTopic(location, "tractor_telemetry_geolocation")
    kafka.writeToTopic(fuel, "tractor_telemetry_fuel")
  }
}

object ParserJob {
  def apply(): ParserJob = new ParserJob()
}