package com.iotplatform.executors.flink.kafka

import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper

final class JsonDeserializationSchema[T: TypeInformation] extends DeserializationSchema[T] {
  private val objectMapper = new ObjectMapper()

  override def deserialize(message: Array[Byte]): T = {
    objectMapper.readValue(message, getProducedType.getTypeClass)
  }

  override def isEndOfStream(nextElement: T): Boolean = false

  override def getProducedType: TypeInformation[T] = implicitly[TypeInformation[T]]
}
