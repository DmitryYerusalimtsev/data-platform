package com.iotplatform.core.config

import com.typesafe.config.{ConfigBeanFactory, ConfigFactory}

import java.io.File
import scala.reflect.{ClassTag, classTag}

trait TypesafeConfigProvider {
  def configFrom[C: ClassTag](path: String): C = {
    val config =
      if (path.isEmpty) ConfigFactory.load
      else ConfigFactory.parseFile(new File(path))

    ConfigBeanFactory.create[C](config,
      classTag[C].runtimeClass.asInstanceOf[Class[C]])
  }
}