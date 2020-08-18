package com.iotplatform.initializer.ignite

import com.iotplatform.domain.devices.Speedometer
import org.apache.ignite.Ignition

object IgniteInitializer {

    fun init() {
        Ignition.start("local-ignite.xml")

        Cache<Long, Speedometer>("speedometers").create()
    }
}