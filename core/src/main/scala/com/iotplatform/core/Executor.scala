package com.iotplatform.core

trait Executor[-J <: Job] {
  def run(job: J): Unit
}
