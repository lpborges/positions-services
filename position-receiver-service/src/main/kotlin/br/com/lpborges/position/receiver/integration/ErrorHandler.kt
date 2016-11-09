package br.com.lpborges.position.receiver.integration

import br.com.lpborges.position.receiver.vo.ReceiverPosition
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.logging.LogFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.MessagingException

/**
 * Created by Leandro Borges on 11/8/16.
 *
 * Error handler for MessagingException from errorChannel
 */
class ErrorHandler(private val objectMapper: ObjectMapper) {

    private val log = LogFactory.getLog(javaClass.name)

    @StreamListener("errorChannel")
    fun handle(messagingException: MessagingException) {
        try {
            val payload = messagingException.failedMessage.payload as ByteArray
            val receiverPosition = objectMapper.readValue(payload, ReceiverPosition::class.java)
            log.error("Error position $receiverPosition received from errorChannel")
        } catch (e: Throwable) {
            log.error("Error to deserialize position from errorChannel", e)
        }
    }
}
