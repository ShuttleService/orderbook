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
import java.time.Instant
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class TradeHistoryApiTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    @MockBean
    lateinit var repository: TradeRepository

    @Test
    fun gettingTradeHistoryDelegatesToTheRepository(){
        val id = UUID.randomUUID().toString()
        val trade = Trade(Side.sell, 0.00174457, 545767, CurrencyPair.ZARBTC, 454,
            Instant.now(), 11334343843358595, id, 952.3435353343)

        BDDMockito.given(repository.findByCurrencyPair(trade.currencyPair)).willReturn(listOf(trade))

        mockMvc.perform(MockMvcRequestBuilders.get("/${trade.currencyPair}/tradehistory")).
        andExpect(MockMvcResultMatchers.status().isOk).
        andExpect(MockMvcResultMatchers.jsonPath("$").isArray).
        andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(trade.id)).
        andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyPair").value(trade.currencyPair.name))

        BDDMockito.then(repository).should().findByCurrencyPair(trade.currencyPair)
    }
}