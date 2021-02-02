package com.agriculture.parser

import com.iotplatform.core.{Application, Executor}
import com.iotplatform.executors.flink.Flink

object ParserApplication extends Application[ParserJob] {
  override protected def executor: Executor[ParserJob] = Flink()
}
