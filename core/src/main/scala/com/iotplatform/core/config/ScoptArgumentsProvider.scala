package com.iotplatform.core.config

import com.iotplatform.core.Arguments
import scopt.OptionParser

trait ScoptArgumentsProvider extends ArgumentsProvider {
  override def argumentsFrom(args: Array[String]): Arguments = {
    val parser = new OptionParser[Arguments]("job") {
      head("scopt", "4.x")

      opt[String]('c', "config")
        .optional()
        .action((path, parameters) => parameters.copy(configPath = path))
        .text("Path to config file")

      opt[Map[String, String]]("args")
        .optional()
        .action((args, parameters) => parameters.copy(args = args))
        .text("Other arguments")
    }
    parser.parse(args, Arguments()) match {
      case Some(value) => value
      case None => throw new IllegalArgumentException("Failed to parse arguments")
    }
  }
}
