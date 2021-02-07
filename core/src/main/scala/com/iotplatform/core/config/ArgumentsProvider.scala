package com.iotplatform.core.config

import com.iotplatform.core.Arguments

trait ArgumentsProvider {
  def argumentsFrom(args: Array[String]): Arguments
}
