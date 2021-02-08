package com.dataplatform.executors.flink.kafka

import com.dataplatform.executors.flink.kafka.serde.{JsonDeserializationSchema, JsonSerializationSchema}
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.datastream.DataStreamSink
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}

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

  def writeToTopic[A: TypeInformation](stream: DataStream[A], topic: String): DataStreamSink[A] = {

    val producer = new FlinkKafkaProducer[A](
      topic,
      new JsonSerializationSchema[A](topic),
      properties,
      FlinkKafkaProducer.Semantic.EXACTLY_ONCE)

    stream.addSink(producer)
  }
}

object Kafka {
  def apply(config: KafkaConfig)(implicit env: StreamExecutionEnvironment): Kafka =
    apply(config.servers, config.groupId)

  def apply(servers: String, groupId: String)(implicit env: StreamExecutionEnvironment): Kafka =
    new Kafka(servers, groupId, env)
}