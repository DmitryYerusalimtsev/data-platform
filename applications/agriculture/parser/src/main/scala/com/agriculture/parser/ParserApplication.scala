package com.agriculture.parser

import com.dataplatform.core.{Application, Executor}
import com.dataplatform.executors.flink.Flink

object ParserApplication extends Application[ParserJob] {
  override protected def executor: Executor[ParserJob] = Flink()
}
