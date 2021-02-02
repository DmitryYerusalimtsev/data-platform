package com.iotplatform.executors.flink.kafka

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema
import org.apache.kafka.clients.producer.ProducerRecord

import java.lang
import scala.util.{Failure, Try}

final class JsonSerializationSchema[T](topic: String) extends KafkaSerializationSchema[T] {
  private val objectMapper = new ObjectMapper()

  override def serialize(element: T, timestamp: lang.Long): ProducerRecord[Array[Byte], Array[Byte]] = {
    Try {
      new ProducerRecord[T](topic, objectMapper.writeValueAsBytes(element))
    } match {
      case Failure(ex) => throw new IllegalArgumentException("Could not serialize record: " + element, ex)
    }
  }
}