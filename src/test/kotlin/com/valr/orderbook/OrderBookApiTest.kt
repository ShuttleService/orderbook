package com.valr.orderbook

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class OrderBookApiTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var repository: OrderRepository
    private val sell = Order(Side.sell, 0.48121022,545768,CurrencyPair.BTCZAR,5)
    private val buy = Order(Side.buy, 1.48121022,1545768,CurrencyPair.ZARBTC,3)

    @Test
    fun orderBookDelegatesToTheRepository(){
        BDDMockito.given(repository.findByCurrencyPair(CurrencyPair.BTCZAR)).willReturn(listOf(sell, buy))

        mockMvc.perform(MockMvcRequestBuilders.get("/${CurrencyPair.BTCZAR.name}/orderbook")).
        andExpect(MockMvcResultMatchers.status().isOk).
        andExpect(MockMvcResultMatchers.jsonPath("$.asks").isArray).
        andExpect(MockMvcResultMatchers.jsonPath("$.asks[0].side").value(sell.side.name)).
        andExpect(MockMvcResultMatchers.jsonPath("$.asks[0].price").value(sell.price)).
        andExpect(MockMvcResultMatchers.jsonPath("$.bids[0].side").value(buy.side.name)).
        andExpect(MockMvcResultMatchers.jsonPath("$.bids[0].price").value(buy.price))

        BDDMockito.then(repository).should().findByCurrencyPair(CurrencyPair.BTCZAR)
    }
}