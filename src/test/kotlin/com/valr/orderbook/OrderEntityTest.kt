package com.valr.orderbook


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class OrderEntityTest {

    @Autowired
    lateinit var repository: OrderRepository

    @Test
    fun persistsToRepository(){

        val order =  Order(Side.sell, 0.48121022,545768,CurrencyPair.BTCZAR,5)
        val saved = repository.save(order)
        Assertions.assertTrue(saved.orderId > 0)
    }
}