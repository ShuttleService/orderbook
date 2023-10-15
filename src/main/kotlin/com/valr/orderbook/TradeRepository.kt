package com.valr.orderbook

import org.springframework.data.jpa.repository.JpaRepository

interface TradeRepository: JpaRepository<Trade, String>{
    fun findByCurrencyPair(currencyPair: CurrencyPair):List<Trade>
}