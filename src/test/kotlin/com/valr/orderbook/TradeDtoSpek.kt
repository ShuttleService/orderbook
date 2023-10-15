package com.valr.orderbook

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Instant
import java.util.*
import kotlin.test.assertEquals

object TradeDtoSpek : Spek({
    describe("Trade Dto Spek") {

        it("sets the relevant fields correctly"){
            val tradedAt: Instant = Instant.now()
            val id = UUID.randomUUID().toString()
            val trade = Trade(Side.sell, 0.00174457, 545767, CurrencyPair.ZARBTC, 454, tradedAt, 11334343843358595, id, 952.3435353343)
            val tradeDto = TradeDto(trade)

            assertEquals(trade.takerSide, tradeDto.takerSide)
            assertEquals(trade.quantity, tradeDto.quantity)
            assertEquals(trade.price, tradeDto.price)
            assertEquals(trade.currencyPair, tradeDto.currencyPair)
            assertEquals(trade.tradedAt, tradeDto.tradedAt)
            assertEquals(trade.sequenceId, tradeDto.sequenceId)
            assertEquals(trade.id, tradeDto.id)
            assertEquals(trade.quoteVolume, tradeDto.quoteVolume)
        }
    }
})