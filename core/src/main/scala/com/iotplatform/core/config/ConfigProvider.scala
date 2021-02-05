package com.iotplatform.core.config

import scala.reflect.ClassTag

trait ConfigProvider {
  def configFrom[C: ClassTag](filePath: String): C
}