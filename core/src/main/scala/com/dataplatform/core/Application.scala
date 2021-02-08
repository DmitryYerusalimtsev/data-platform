package com.dataplatform.core

import com.dataplatform.core.config.ScoptArgumentsProvider
import com.typesafe.scalalogging.LazyLogging

import scala.reflect.{ClassTag, classTag}
import scala.util.{Failure, Success, Try}

abstract class Application[J <: ConfiguredJob[_] : ClassTag]
  extends ScoptArgumentsProvider
    with LazyLogging {

  protected val job: J = classTag[J].runtimeClass.newInstance.asInstanceOf[J]

  protected def executor: Executor[J]

  def main(args: Array[String]): Unit = {
    val arguments = argumentsFrom(args)
    job.setArguments(arguments)

    run(job, executor)
  }

  protected def run(job: J, executor: Executor[J]): Unit = {
    logger.info(s"Start executing job '${job.name}' with executor '${executor.getClass.getName}'.")

    Try(executor.run(job)) match {
      case Success(_) => logger.info(s"Execution of job '${job.name}' successfully finished.")
      case Failure(ex) => logger.error(s"Execution of job '${job.name}' finished with error.", ex)
    }
  }
}