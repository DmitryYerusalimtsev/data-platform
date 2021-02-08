package com.agriculture.parser

import com.dataplatform.executors.flink.kafka.KafkaConfig

import scala.beans.BeanProperty

class ParserConfig {
  @BeanProperty var sourceTopic: String = _
  @BeanProperty var geolocationTopic: String = _
  @BeanProperty var fuelTopic: String = _
  @BeanProperty var kafka: KafkaConfig = _
}