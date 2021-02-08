package com.dataplatform.initializer.ignite

import com.dataplatform.domain.devices.Speedometer
import org.apache.ignite.Ignition

object IgniteInitializer {

    fun init() {
        Ignition.start("local-ignite.xml")

        Cache<Long, Speedometer>("speedometers").create()
    }
}