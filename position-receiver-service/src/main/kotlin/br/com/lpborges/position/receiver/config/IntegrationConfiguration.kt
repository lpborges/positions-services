package br.com.lpborges.position.receiver.config

import br.com.lpborges.position.receiver.integration.PositionProducerListener
import br.com.lpborges.position.receiver.integration.PositionsChannel
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.ProducerListener

/**
* Created by leandropg on 11/5/16.
*/
@Configuration
@EnableBinding(PositionsChannel::class)
open class IntegrationConfiguration {

    @Bean
    open fun producerListener(objectMapper: ObjectMapper): ProducerListener<ByteArray, ByteArray>
            = PositionProducerListener(objectMapper)
}
