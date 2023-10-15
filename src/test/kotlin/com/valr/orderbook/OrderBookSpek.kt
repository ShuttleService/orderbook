package com.valr.orderbook

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertSame

class OrderBookSpek : Spek({

    describe("OrderBook Spek") {
        it("Allocates Asks and Bids correctly") {
            val sell = Order(Side.sell, 0.48121022, 545768, CurrencyPair.BTCZAR, 5)
            val buy = Order(Side.buy, 1.48121022, 1545768, CurrencyPair.ZARBTC, 3)
            val now = Instant.now()
            val orderBook = OrderBook(listOf(sell, buy), now, 1233)

            assertEquals(orderBook.lastChange, now)
            assertEquals(orderBook.sequenceNumber, 1233)
            assertSame(orderBook.asks[0], sell)
            assertSame(orderBook.bids[0], buy)
        }
    }
})