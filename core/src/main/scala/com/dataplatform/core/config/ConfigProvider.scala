package com.dataplatform.core.config

import scala.reflect.ClassTag

trait ConfigProvider {
  def configFrom[C: ClassTag](path: String): C
}
