package com.iotplatform.core.config

import pureconfig._

import scala.reflect.ClassTag

trait PureConfigProvider extends ConfigProvider {
  override def configFrom[C: ClassTag](path: String): C = {
    val source =
      if (path.isEmpty) ConfigSource.default
      else ConfigSource.file(path)

    source.loadOrThrow[C]
  }
}
