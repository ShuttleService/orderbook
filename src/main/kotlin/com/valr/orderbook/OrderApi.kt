package com.valr.orderbook

import com.valr.security.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApi {

    val logger: Logger = LoggerFactory.getLogger(OrderApi::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var repository: OrderRepository

    @PostMapping("/v1/orders/limit")
    fun saveOrder(@RequestBody order: Order, @AuthenticationPrincipal authenticatedUser: UserDetails) {
        logger.info("Saving order $order by ${authenticatedUser.username}")
        val user = userRepository.findByUsername(authenticatedUser.username)
        order.user = user.get()
        repository.save(order)
    }
}