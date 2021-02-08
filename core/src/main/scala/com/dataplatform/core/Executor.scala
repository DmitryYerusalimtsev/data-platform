package com.dataplatform.core

trait Executor[-J <: Job] {
  def run(job: J): Unit = job.script()
}
