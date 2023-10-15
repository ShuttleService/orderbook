package com.valr.orderbook

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TradeApi {

    private val logger: Logger = LoggerFactory.getLogger(TradeApi::class.java)

    @Autowired
    private lateinit var repository: TradeRepository

    @GetMapping("/{currencyPair}/tradehistory")
    fun tradeHistory(@PathVariable("currencyPair") currencyPair: CurrencyPair): List<TradeDto> {
        logger.info("Finding trade history for currency pair $currencyPair")
        val trades = repository.findByCurrencyPair(currencyPair)
        logger.info("Found ${trades.size} by currency pair $currencyPair")

       return trades.map { TradeDto(it) }
    }
}