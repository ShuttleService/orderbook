package com.valr.orderbook

data class TradeDto(private val trade: Trade) {
    val takerSide = trade.takerSide
    val quantity = trade.quantity
    val price = trade.price
    val currencyPair = trade.currencyPair
    val tradedAt = trade.tradedAt
    val sequenceId = trade.sequenceId
    val id = trade.id
    val quoteVolume = trade.quoteVolume
}