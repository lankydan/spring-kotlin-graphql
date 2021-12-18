package dev.lankydan.people.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("dev.lankydan.people")
@ComponentScan("dev.lankydan.people")
@EnableJpaRepositories("dev.lankydan.people")
class Application

fun main() {
  SpringApplication.run(Application::class.java)
}