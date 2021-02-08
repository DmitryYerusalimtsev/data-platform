package com.dataplatform.executors.flink.kafka.serde

import com.google.gson._

import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private class LocalDateTimeAdapter extends JsonSerializer[LocalDateTime]
  with JsonDeserializer[LocalDateTime] {

  override def serialize(src: LocalDateTime, typeOfSrc: Type,
                         context: JsonSerializationContext): JsonElement = {
    new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
  }

  override def deserialize(json: JsonElement, typeOfT: Type,
                           context: JsonDeserializationContext): LocalDateTime = {
    LocalDateTime.parse(json.getAsJsonPrimitive.getAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
  }
}
