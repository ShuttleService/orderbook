package com.valr.orderbook

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant
import java.util.*
import kotlin.test.assertTrue

@SpringBootTest
class TradeEntityTest {
    @Autowired
    lateinit var repository: TradeRepository

    @Test
    fun persistsTrades() {
        val trade = Trade(
            Side.sell, 0.00174457, 545767, CurrencyPair.ZARBTC, 454, Instant.now(), 11334343843358595,
            UUID.randomUUID().toString(), 952.3435353343
        )

        val saved = repository.save(trade)
        assertTrue(saved.orderId > 0 )
    }
}