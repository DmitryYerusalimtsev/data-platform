package com.dataplatform.initializer.ignite

import org.apache.ignite.Ignition
import org.apache.ignite.cache.CacheAtomicityMode
import org.apache.ignite.configuration.CacheConfiguration

open class Cache<K, V>(private val name: String,
                       private val atomicityMode: CacheAtomicityMode) {

    constructor(name: String) : this(name, CacheAtomicityMode.ATOMIC)

    private val ignite = Ignition.ignite()

    open fun create() {
        val config = CacheConfiguration<K, V>()

        config.name = name
        config.atomicityMode = atomicityMode
        config.backups = 1

        ignite.getOrCreateCache(config)
    }
}