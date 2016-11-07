package br.com.lpborges.position.receiver.vo

import java.time.ZonedDateTime

/**
 * Created by leandropg on 10/10/16.
 */
data class ReceiverPosition(val Long: ZonedDateTime, val trackerPosition: TrackerPosition)
