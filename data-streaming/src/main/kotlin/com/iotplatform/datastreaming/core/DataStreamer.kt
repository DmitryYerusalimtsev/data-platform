package com.iotplatform.datastreaming.core

import org.apache.ignite.Ignition
import org.apache.ignite.stream.StreamMultipleTupleExtractor
import org.apache.ignite.stream.kafka.KafkaStreamer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

open class DataStreamer<K, V>(topic: String,
                              cacheName: String,
                              consumerGroup: String,
                              extractor: StreamMultipleTupleExtractor<ConsumerRecord<*, *>, K, V>,
                              numThreads: Int) {

    constructor(topic: String,
                cacheName: String,
                consumerGroup: String,
                extractor: StreamMultipleTupleExtractor<ConsumerRecord<*, *>, K, V>)
            : this(topic, cacheName, consumerGroup, extractor, 4)

    private var streamer: KafkaStreamer<K, V>

    init {
        val ignite = Ignition.ignite()
        val dataStreamer = ignite.dataStreamer<K, V>(cacheName)

        streamer = KafkaStreamer()
        streamer.ignite = ignite
        streamer.streamer = dataStreamer
        streamer.setTopic(listOf(topic))
        streamer.setThreads(numThreads)

        streamer.multipleTupleExtractor = extractor

        val consumerConfig = Properties()
        consumerConfig["bootstrap.servers"] = "localhost:9092"
        consumerConfig["group.id"] = consumerGroup
        consumerConfig["key.deserializer"] = StringDeserializer::class.qualifiedName
        consumerConfig["value.deserializer"] = StringDeserializer::class.qualifiedName

        streamer.setConsumerConfig(consumerConfig)
    }

    fun start() {
        streamer.start()
    }

    fun stop() {
        streamer.stop()
    }
}