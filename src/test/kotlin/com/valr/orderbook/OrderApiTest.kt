package com.valr.orderbook

import com.fasterxml.jackson.databind.ObjectMapper
import com.valr.security.User
import com.valr.security.UserRepository
import com.valr.security.UserService
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito
import org.mockito.Captor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiTest {
    private val username = "user"
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var mapper:ObjectMapper
    @Autowired
    private lateinit var userService: UserService

    @MockBean
    private lateinit var repository: OrderRepository
    @MockBean
    private lateinit var userRepository: UserRepository
    @Captor
    private lateinit var captor: ArgumentCaptor<Order>


    @Test
    @WithMockUser
    fun savingAnOrderFindsAndSetsTheUser() {
        val user = User(username, "pass")
        BDDMockito.given(userRepository.findByUsername(username)).willReturn(Optional.of(user))
        val order = Order(Side.sell, 0.48121022,545768,CurrencyPair.BTCZAR,5)
        val orderString = mapper.writeValueAsString(order)

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/orders/limit").contentType(MediaType.APPLICATION_JSON)
            .content(orderString)).
        andExpect(MockMvcResultMatchers.status().isOk)

        BDDMockito.then(userRepository).should(BDDMockito.atLeastOnce()).findByUsername(username)
        BDDMockito.then(repository).should().save(captor.capture())

        val actual = captor.value
        Assertions.assertEquals(username, actual.user?.username)
    }
}