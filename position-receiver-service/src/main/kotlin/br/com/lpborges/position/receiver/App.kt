package br.com.lpborges.position.receiver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by Leandro Borges on 10/10/16.
 *
 * Main Spring Boot application class
 */
@SpringBootApplication
open class App()

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
