package br.com.lpborges.position.receiver.integration

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

/**
 * Created by Leandro Borges on 10/17/16.
 *
 * Integration channel for sending positions
 */
interface PositionsSource {

    @Output
    fun output(): MessageChannel
}
