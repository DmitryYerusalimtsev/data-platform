package com.iotplatform.core

import com.iotplatform.core.config.{PureConfigProvider, ScoptArgumentsProvider}
import com.typesafe.scalalogging.LazyLogging

import scala.reflect.{ClassTag, classTag}
import scala.util.{Failure, Success, Try}

abstract class Application[C, J <: Job[C] : ClassTag]
  extends PureConfigProvider
    with ScoptArgumentsProvider
    with LazyLogging {

  protected val job: J = classTag[J].runtimeClass.newInstance.asInstanceOf[J]

  protected def executor: Executor[J]

  def main(args: Array[String]): Unit = {
    val arguments = argumentsFrom(args)
    val config = configFrom(arguments.configPath)

    job.setConfig(config)
    run(job, executor)
  }

  protected def run(job: J, executor: Executor[J]): Unit = {
    logger.info(s"Start executing job '${job.name}' with executor '${executor.getClass.getSimpleName}'.")

    Try(executor.run(job)) match {
      case Success(_) => logger.info(s"Execution of job '${job.name}' successfully finished.")
      case Failure(ex) => logger.error(s"Execution of job '${job.name}' finished with error.", ex)
    }
  }
}