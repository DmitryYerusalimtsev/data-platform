package com.dataplatform.datastreaming

import com.dataplatform.datastreaming.core.DataStreamer
import com.dataplatform.datastreaming.core.JsonExtractor
import com.dataplatform.domain.devices.Speedometer
import org.apache.ignite.Ignition

fun main(args: Array<String>) {
    Ignition.start("local-ignite.xml")

    val streamer = DataStreamer(
            "speedometer_created",
            "speedometers",
            "speedometer_streamer",
            JsonExtractor(Speedometer::class))

    Runtime.getRuntime().addShutdownHook(
            Thread {
                streamer.stop()
            }
    )

    streamer.start()
}