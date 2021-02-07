package com.iotplatform.executors.flink

import com.iotplatform.core.ConfiguredJob
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

import scala.reflect.ClassTag

abstract class FlinkJob[C: ClassTag] extends ConfiguredJob[C] {
  private var _env: StreamExecutionEnvironment = _

  implicit lazy val env: StreamExecutionEnvironment = _env

  private[flink] def setExecutionEnv(env: StreamExecutionEnvironment): Unit = {
    _env = env
  }
}
