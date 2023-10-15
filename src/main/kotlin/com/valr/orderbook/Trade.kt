package com.valr.orderbook

import jakarta.persistence.Entity
import java.time.Instant

@Entity
data class Trade(
    val takerSide: Side,
    override val quantity: Double,
    override val price: Long,
    override val currencyPair: CurrencyPair,
    override val orderCount: Int,
    val tradedAt: Instant,
    val sequenceId: Long,
    val id: String,
    val quoteVolume: Double
) : Order(takerSide,quantity,price,currencyPair,orderCount)