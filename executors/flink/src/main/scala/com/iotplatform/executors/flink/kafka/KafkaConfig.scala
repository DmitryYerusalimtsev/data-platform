package com.iotplatform.executors.flink.kafka

import scala.beans.BeanProperty

class KafkaConfig {
  @BeanProperty var servers: String = _
  @BeanProperty var groupId: String = _
}