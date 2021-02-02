package com.iotplatform.executors.flink

import com.iotplatform.core.Executor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

class Flink extends Executor[FlinkJob] {
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

  override def run(job: FlinkJob): Unit = {
    job.setExecutionEnv(env)
    super.run(job)
  }
}
