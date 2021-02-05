package com.iotplatform.executors.flink.kafka.serde

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema
import org.apache.kafka.clients.producer.ProducerRecord

import java.lang

final class JsonSerializationSchema[T: TypeInformation](topic: String) extends KafkaSerializationSchema[T]
  with GsonProvider {

  override def serialize(element: T, timestamp: lang.Long): ProducerRecord[Array[Byte], Array[Byte]] = {
    try {
      val json = gson.toJson(element)
      new ProducerRecord(topic, json.getBytes())
    }
    catch {
      case ex: Exception => throw new IllegalArgumentException("Could not serialize record: " + element, ex)
    }
  }
}
