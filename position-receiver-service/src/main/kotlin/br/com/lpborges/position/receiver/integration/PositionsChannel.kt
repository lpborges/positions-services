package br.com.lpborges.position.receiver.integration

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

/**
* Created by leandropg on 10/17/16.
*/
interface PositionsChannel {

    @Output
    fun output(): MessageChannel
}
