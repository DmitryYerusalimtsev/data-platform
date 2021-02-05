package com.iotplatform.core

import com.typesafe.scalalogging.LazyLogging

trait Job[C] extends LazyLogging {
  def name: String = this.getClass.getSimpleName
    .replace("Job", "")

  def script(): Unit

  private var _config: C = _
  protected lazy val config: C = _config

  private[core] def setConfig(config: C): Unit =
    _config = config
}
