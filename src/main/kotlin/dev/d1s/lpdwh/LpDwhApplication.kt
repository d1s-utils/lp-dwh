package dev.d1s.lpdwh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LpDwhApplication

fun main(args: Array<String>) {
    runApplication<LpDwhApplication>(*args)
}
