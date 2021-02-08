package com.dataplatform.core.config

import com.dataplatform.core.Arguments

trait ArgumentsProvider {
  def argumentsFrom(args: Array[String]): Arguments
}
