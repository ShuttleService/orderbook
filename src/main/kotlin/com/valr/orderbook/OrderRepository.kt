package com.valr.orderbook

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository:JpaRepository<Order, Long>{
    fun findByCurrencyPair(currencyPair: CurrencyPair): List<Order>
}