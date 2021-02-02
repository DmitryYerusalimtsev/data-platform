package com.iotplatform.executors.flink

import com.iotplatform.core.Job
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

abstract class FlinkJob extends Job {
  private var _env: StreamExecutionEnvironment = _

  implicit lazy val env: StreamExecutionEnvironment = _env

  private[flink] def setExecutionEnv(env: StreamExecutionEnvironment): Unit = {
    _env = env
  }
}
