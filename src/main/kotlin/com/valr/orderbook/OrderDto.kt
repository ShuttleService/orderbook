package com.valr.orderbook

data class OrderDto(private val order: Order) {
    val side = order.side
    val quantity = order.quantity
    val price = order.price
    val currencyPair = order.currencyPair
    val orderCount = order.orderCount
}