package com.dataplatform.executors.flink.kafka.serde

import org.apache.flink.api.common.serialization.DeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation

final class JsonDeserializationSchema[T: TypeInformation] extends DeserializationSchema[T]
  with GsonProvider {

  override def deserialize(message: Array[Byte]): T = {
    val json = message.map(_.toChar).mkString
    gson.fromJson[T](json, getProducedType.getTypeClass)
  }

  override def isEndOfStream(nextElement: T): Boolean = false

  override def getProducedType: TypeInformation[T] = implicitly[TypeInformation[T]]
}
