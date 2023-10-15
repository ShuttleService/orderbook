package com.valr

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Home {

    @GetMapping("/")
    fun home() = "pong"

    @PostMapping("/")
    fun root() = "root"
}