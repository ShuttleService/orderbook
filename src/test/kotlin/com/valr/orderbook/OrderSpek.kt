package com.valr.orderbook

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object OrderSpek :Spek({

    describe("Order"){

        val order = Order(Side.sell, 0.48121022,545768,CurrencyPair.BTCZAR,5)
        it("sets the appropriate values"){

            assertEquals(Side.sell, order.side)
            assertEquals(0.48121022,order.quantity)
            assertEquals(545768,order.price)
            assertEquals(CurrencyPair.BTCZAR,order.currencyPair)
            assertEquals(5,order.orderCount)
        }
    }
})