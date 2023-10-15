package com.valr.orderbook

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class OrderBookApi {

    private val logger: Logger = LoggerFactory.getLogger(OrderBookApi::class.java)

    @Autowired
    private lateinit var repository: OrderRepository

    @GetMapping("/{currencyPair}/orderbook")
    fun orderBook(@PathVariable("currencyPair") currencyPair: CurrencyPair): OrderBook {
        logger.info("finding order book for currency pair $currencyPair")
        val orders: List<Order> = repository.findByCurrencyPair(currencyPair)
        logger.info("Found ${orders.size}  orders with currency pair $currencyPair")

        return OrderBook(orders, Instant.now(), 1)
    }
}