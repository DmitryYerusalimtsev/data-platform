package com.dataplatform.domain.devices

abstract class Device(
        id: Long,
        val name: String
) : HasId<Long>