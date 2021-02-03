package com.iotplatform.executors.flink.kafka

import com.iotplatform.executors.flink.kafka.serde.JsonDeserializationSchema
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

import java.util.Properties

class Kafka(servers: String, groupId: String, env: StreamExecutionEnvironment) {

  private val properties = {
    val props = new Properties()
    props.setProperty("bootstrap.servers", servers)
    props.setProperty("group.id", groupId)
    props
  }

  def readTopic[A: TypeInformation](topic: String): DataStream[A] = {
    val consumer = new FlinkKafkaConsumer[A](topic, new JsonDeserializationSchema[A], properties)

    env.addSource(consumer)(createTypeInformation[A])
  }
}

object Kafka {
  def apply(servers: String, groupId: String)(implicit env: StreamExecutionEnvironment): Kafka =
    new Kafka(servers, groupId, env)
}