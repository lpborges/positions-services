package br.com.lpborges.position.receiver.vo

/**
 * Created by Leandro Borges on 10/10/16.
 *
 * Data class for a TrackerPosition with the received server time
 */
data class ReceiverPosition(val serverTime: Long, val trackerPosition: TrackerPosition)
