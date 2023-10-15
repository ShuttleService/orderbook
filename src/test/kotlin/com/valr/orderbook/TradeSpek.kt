package com.valr.orderbook

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Instant
import java.util.UUID
import kotlin.test.assertEquals

class TradeSpek : Spek({

    describe("Trade Spek") {

        it("Sets appropriate values") {
            val tradedAt: Instant = Instant.now()
            val id = UUID.randomUUID().toString()
            val trade = Trade(
                Side.sell, 0.00174457,
                545767, CurrencyPair.ZARBTC, 454, tradedAt, 11334343843358595,
                id, 952.3435353343
            )

            assertEquals(545767, trade.price)
            assertEquals(0.00174457, trade.quantity)
            assertEquals(CurrencyPair.ZARBTC, trade.currencyPair)
            assertEquals(tradedAt, trade.tradedAt)
            assertEquals(Side.sell, trade.takerSide)
            assertEquals(11334343843358595, trade.sequenceId)
            assertEquals(id, trade.id)
            assertEquals(952.3435353343, trade.quoteVolume)
        }
    }
})