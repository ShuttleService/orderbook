package com.valr.orderbook

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object OrderDtoSpek : Spek({

    describe("Order DTO Spek") {

        it("sets the relevant fields correctly") {
            val order = Order(Side.sell, 0.48121022, 545768, CurrencyPair.BTCZAR, 5)
            val orderDto: OrderDto = OrderDto(order)

            assertEquals(order.side, orderDto.side)
            assertEquals(order.quantity, orderDto.quantity)
            assertEquals(order.price, orderDto.price)
            assertEquals(order.currencyPair, orderDto.currencyPair)
            assertEquals(order.orderCount, orderDto.orderCount)
        }
    }
})