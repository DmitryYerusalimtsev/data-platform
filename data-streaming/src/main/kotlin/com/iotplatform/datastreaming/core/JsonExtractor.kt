package com.iotplatform.datastreaming.core

import com.google.gson.Gson
import com.iotplatform.domain.devices.HasId
import org.apache.ignite.stream.StreamMultipleTupleExtractor
import org.apache.kafka.clients.consumer.ConsumerRecord
import kotlin.reflect.KClass

class JsonExtractor<K, V : HasId<K>>(private val clazz: KClass<V>)
    : StreamMultipleTupleExtractor<ConsumerRecord<*, *>, K, V> {

    override fun extract(msg: ConsumerRecord<*, *>): MutableMap<K, V> {
        val obj = Gson().fromJson<V>(msg.value().toString(), clazz.java)
        val result = if (obj != null) {
            mapOf(Pair(obj.id, obj))
        } else emptyMap()

        return result.toMutableMap()
    }
}