package br.com.lpborges.position.receiver.integration

import br.com.lpborges.position.receiver.vo.ReceiverPosition
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.kafka.support.ProducerListener
import org.springframework.kafka.support.serializer.JsonDeserializer

/**
 * Created by leandropg on 11/4/16.
 */
class PositionProducerListener : ProducerListener<ByteArray, ByteArray> {

    val deserializer: JsonDeserializer<ReceiverPosition>

    constructor(objectMapper: ObjectMapper) {
        deserializer = JsonDeserializer(ReceiverPosition::class.java, objectMapper)
    }

    override fun isInterestedInSuccess(): Boolean = false

    override fun onSuccess(topic: String?, partition: Int?, key: ByteArray?,
                           value: ByteArray?, recordMetadata: RecordMetadata?) = Unit

    override fun onError(topic: String?, partition: Int?, key: ByteArray?, value: ByteArray?, exception: Exception?) =
            deserializer.use {
                val receiverPosition = it.deserialize(topic, value)
                println("Error to send position $receiverPosition")
            }
}
