package com.iotplatform.executors.flink.kafka.serde

import com.google.gson._

import java.lang.reflect.Type
import java.sql.Timestamp
import java.time.ZonedDateTime

private[serde] class TimestampAdapter extends JsonDeserializer[Timestamp] {

  override def deserialize(json: JsonElement, typeOfT: Type,
                           context: JsonDeserializationContext): Timestamp = {
    val zonedDateTime = ZonedDateTime.parse(json.getAsJsonPrimitive.getAsString)
    Timestamp.from(zonedDateTime.toInstant)
  }
}
