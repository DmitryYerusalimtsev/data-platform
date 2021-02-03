package com.iotplatform.executors.flink.kafka.serde

import java.lang.reflect.Type
import java.sql.Timestamp
import java.time.{LocalDateTime, ZonedDateTime}
import java.time.format.DateTimeFormatter

import com.google.gson._

private[serde] trait GsonProvider {

  type Deserializer[T] = com.google.gson.JsonDeserializer[T]

  @transient lazy val gson: Gson = {
    val builder = new GsonBuilder()
      .registerTypeAdapter(classOf[LocalDateTime], new Deserializer[LocalDateTime] {
        override def deserialize(json: JsonElement, typeOfT: Type,
                                 context: JsonDeserializationContext): LocalDateTime = {
          LocalDateTime.parse(json.getAsJsonPrimitive.getAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
      })
      .registerTypeAdapter(classOf[Timestamp], new Deserializer[Timestamp] {
        override def deserialize(json: JsonElement, typeOfT: Type,
                                 context: JsonDeserializationContext): Timestamp = {
          val zonedDateTime = ZonedDateTime.parse(json.getAsJsonPrimitive.getAsString)
          Timestamp.from(zonedDateTime.toInstant)
        }
      })

    builder.create()
  }
}