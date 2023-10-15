package com.valr.orderbook

import com.valr.security.User
import jakarta.persistence.*


@Entity
@Table(name = "orders")
open class Order(
    @Enumerated(EnumType.STRING)
    open val side: Side,
    open val quantity: Double,
    open val price: Long,
    @Enumerated(EnumType.STRING)
    open val currencyPair: CurrencyPair,
    open val orderCount: Int
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var orderId: Int = 0

    @ManyToOne
    open var user: User? = null

}