package br.com.lpborges.position.receiver.integration

import org.apache.commons.logging.LogFactory
import org.springframework.cloud.stream.binder.EmbeddedHeadersMessageConverter
import org.springframework.integration.support.MessageBuilder
import org.springframework.kafka.support.ProducerListenerAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandlingException
import org.springframework.messaging.support.ErrorMessage

/**
 * Created by Leandro Borges on 11/8/16.
 *
 * Error Producer listener to sent Kafka errors to errorChannel
 */
class ErrorProducerListener(private val errorChannel: MessageChannel) :
        ProducerListenerAdapter<ByteArray, ByteArray>() {

    private val log = LogFactory.getLog(javaClass.name)

    private val embeddedHeadersMessageConverter = EmbeddedHeadersMessageConverter()

    /**
     * Send the error message to errorChannel
     */
    override fun onError(topic: String?, partition: Int?, key: ByteArray?, value: ByteArray?, exception: Exception?) {
        log.error("Error to sent message to topic=$topic, partition=$partition", exception)
        val messageWithHeaders = MessageBuilder.withPayload(value).build()
        val messageValues = embeddedHeadersMessageConverter.extractHeaders(messageWithHeaders, false)
        val messageHandlingException = MessageHandlingException(messageValues.toMessage(), exception)
        val errorMessage = ErrorMessage(messageHandlingException, messageValues.headers)
        log.debug("Sending message $errorMessage to errorChannel")
        errorChannel.send(errorMessage)
    }
}
