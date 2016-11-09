package br.com.lpborges.position.receiver.config

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
import org.springframework.boot.autoconfigure.web.ErrorAttributes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.context.request.RequestAttributes

/**
 * Created by Leandro Borges on 11/5/16.
 *
 * Configurations for Spring Rest and WebMVC
 */
@Configuration
open class RestConfiguration {

    /**
     * Override Spring default Error attributes to remove the exception from error responses
     */
    @Bean
    open fun errorAttributes(): ErrorAttributes =
            object : DefaultErrorAttributes() {
                override fun getErrorAttributes(requestAttributes: RequestAttributes,
                                                includeStackTrace: Boolean): Map<String, Any> {
                    val errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace)
                    errorAttributes.remove("exception")
                    return errorAttributes
                }
            }
}
