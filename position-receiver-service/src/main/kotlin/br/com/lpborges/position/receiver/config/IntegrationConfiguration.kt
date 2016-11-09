package br.com.lpborges.position.receiver.config

import br.com.lpborges.position.receiver.integration.ErrorHandler
import br.com.lpborges.position.receiver.integration.ErrorProducerListener
import br.com.lpborges.position.receiver.integration.PositionsSource
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.ProducerListener
import org.springframework.messaging.MessageChannel

/**
 * Created by Leandro Borges on 11/5/16.
 *
 * Configurations for Kafka integration
 */
@Configuration
@EnableBinding(PositionsSource::class)
open class IntegrationConfiguration {

    @Bean
    open fun producerListener(errorChannel: MessageChannel): ProducerListener<ByteArray, ByteArray>
            = ErrorProducerListener(errorChannel)

    @Bean
    open fun errorHandler(objectMapper: ObjectMapper): ErrorHandler = ErrorHandler(objectMapper)
}
