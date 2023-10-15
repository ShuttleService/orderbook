package com.valr.orderbook

import java.time.Instant

class OrderBook(private val orders: List<Order>, val lastChange: Instant, val sequenceNumber: Int) {
    val asks = orders.filter { it.side == Side.sell }
    val bids = orders.filter { it.side == Side.buy }
}