package br.com.lpborges.position.receiver.controller

import br.com.lpborges.position.receiver.integration.PositionsChannel
import br.com.lpborges.position.receiver.vo.ReceiverPosition
import br.com.lpborges.position.receiver.vo.TrackerPosition
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.integration.support.MessageBuilder
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.servlet.http.HttpServletResponse

/**
 * Created by leandropg on 10/10/16.
 *
 * Sample TrackerPosition json:
 * <pre>{@code
 *
 *   {
 *     "idTracker": 12345,
 *     "position": {
 *         "time": 123456,
 *         "coordinate": {
 *           "longitude": 5.15,
 *           "latitude": 3.82
 *         }
 *     }
 *   }
 *
 * }</pre>
 */
@RestController
@RequestMapping("/api/positions")
class PositionsController(private val positionsChannel: PositionsChannel) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePosition(@RequestBody position: TrackerPosition): ReceiverPosition {
        val receiverPosition = ReceiverPosition(ZonedDateTime.now(), position)
        positionsChannel.output().send(MessageBuilder.withPayload(receiverPosition).build())
        return receiverPosition
    }

    @ExceptionHandler(HttpMessageConversionException::class)
    fun messageErrorHandler(response: HttpServletResponse)
            = response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid request body.")
}
