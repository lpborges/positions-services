package br.com.lpborges.position.receiver.controller

import br.com.lpborges.position.receiver.integration.PositionsSource
import br.com.lpborges.position.receiver.vo.ReceiverPosition
import br.com.lpborges.position.receiver.vo.TrackerPosition
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageConversionException
import org.springframework.integration.support.MessageBuilder
import org.springframework.messaging.MessageChannel
import org.springframework.web.bind.annotation.*
import sun.plugin2.message.Message
import javax.servlet.http.HttpServletResponse

/**
 * Created by Leandro Borges on 10/10/16.
 *
 * Rest Controller for TrackerPositions
 */
@RestController
@RequestMapping("/api/positions")
class PositionsController(positionsSource: PositionsSource) {

    val positionsChannel: MessageChannel

    init {
        positionsChannel = positionsSource.output()
    }

    /**
     * Save a TrackerPosition
     *
     * <pre>{@code
     *   // sample json
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
     * }</pre>
     *
     * @param position TrackerPosition
     * @return ReceiverPosition, a position with the server received time
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePosition(@RequestBody position: TrackerPosition): ReceiverPosition {
        val receiverPosition = ReceiverPosition(System.currentTimeMillis(), position)
        positionsChannel.send(MessageBuilder.withPayload(receiverPosition).build())
        return receiverPosition
    }

    /**
     * Handle to a more simple message
     *
     * @param response HttpServletResponse
     */
    @ExceptionHandler(HttpMessageConversionException::class)
    fun messageErrorHandler(response: HttpServletResponse)
            = response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid request body.")
}
