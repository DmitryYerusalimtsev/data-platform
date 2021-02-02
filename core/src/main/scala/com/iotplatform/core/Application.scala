package com.iotplatform.core

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

trait Application[J <: Job] extends LazyLogging {
  protected def job: J

  protected def executor: Executor[J]

  def main(args: Array[String]): Unit = run(job, executor)

  protected def run(job: J, executor: Executor[J]): Unit = {
    logger.info(s"Start executing job '${job.name}' with executor '${executor.getClass.getSimpleName}'.")

    Try(executor.run(job)) match {
      case Success(_) => logger.info(s"Execution of job '${job.name}' successfully finished.")
      case Failure(ex) => logger.error(s"Execution of job '${job.name}' finished with error.", ex)
    }
  }
}