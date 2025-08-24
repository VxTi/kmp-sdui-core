package nl.q42

import lombok.extern.slf4j.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.io.IOException
import java.util.Collections

@Slf4j
@SpringBootApplication
@ComponentScan("nl.q42")
object Main {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val app = SpringApplication(Main::class.java)
        app.setDefaultProperties(Collections.singletonMap<String?, Any?>("server.port", "8080"))
        app.run(*args)
    }
}