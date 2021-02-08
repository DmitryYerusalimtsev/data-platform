package com.agriculture.parser

import com.dataplatform.executors.flink.kafka.KafkaConfig

import scala.beans.BeanProperty

class ParserConfig {
  @BeanProperty var kafka: KafkaConfig = _
}