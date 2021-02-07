package com.iotplatform.core

import com.iotplatform.core.config.TypesafeConfigProvider
import com.typesafe.scalalogging.LazyLogging

import scala.reflect.ClassTag

trait Job {
  def name: String = this.getClass.getSimpleName
    .replace("Job", "")

  def script(): Unit
}

abstract class ConfiguredJob[C: ClassTag] extends Job
  with TypesafeConfigProvider with LazyLogging {

  protected lazy val config: C = configFrom(arguments.configPath)

  private var _args: Arguments = _
  protected lazy val arguments: Arguments = _args

  private[core] def setArguments(args: Arguments): Unit = {
    _args = args
  }
}
