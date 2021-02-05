package com.iotplatform.core.config

case class Arguments(configPath: String, args: Map[String, String] = Map())

trait ArgumentsProvider {
  def argumentsFrom(args: Array[String]): Arguments
}
