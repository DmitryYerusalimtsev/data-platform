package com.iotplatform.core

trait Job {
  def name: String

  def script(): Unit
}
