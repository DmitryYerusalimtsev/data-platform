package com.iotplatform.domain.devices

abstract class Device(
        id: Long,
        val name: String
) : HasId<Long>