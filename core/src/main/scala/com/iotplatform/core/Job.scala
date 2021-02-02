package com.iotplatform.core

trait Job {
  def name: String = this.getClass.getSimpleName
    .replace("Job", "")

  def script(): Unit
}
