package br.com.lpborges.position.receiver.vo

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

/**
 * Created by leandropg on 10/10/16.
 *
 * Data classes for a tracker position
 */

data class Coordinate(val longitude: BigDecimal, val latitude: BigDecimal)

data class Position(val coordinate: Coordinate, @JsonProperty(required = true) val time: Long)

data class TrackerPosition(@JsonProperty(required = true) val idTracker: Long, val position: Position)
