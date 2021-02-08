package com.agriculture.parser

import com.agriculture.domain.TractorTelemetry
import com.dataplatform.executors.flink.FlinkJob
import com.dataplatform.executors.flink.kafka.Kafka
import org.apache.flink.streaming.api.scala._

final class ParserJob extends FlinkJob[ParserConfig] {

  override def script(): Unit = {
    val kafka = Kafka(config.kafka)

    val telemetry = kafka.readTopic[TractorTelemetry](config.sourceTopic)

    val location = telemetry.map(_.location)
    val fuel = telemetry.map(_.fuel)

    kafka.writeToTopic(location, config.geolocationTopic)
    kafka.writeToTopic(fuel, config.fuelTopic)
  }
}
