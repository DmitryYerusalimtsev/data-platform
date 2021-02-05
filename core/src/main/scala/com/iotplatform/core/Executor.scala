package com.iotplatform.core

trait Executor[-J <: Job[_]] {
  def run(job: J): Unit = job.script()
}
