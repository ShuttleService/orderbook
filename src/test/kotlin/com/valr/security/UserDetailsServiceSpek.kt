package com.valr.security

import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.springframework.security.core.userdetails.UsernameNotFoundException
import java.util.*
import kotlin.test.assertEquals


class UserDetailsServiceSpek : Spek({

    describe("User Details Service Spek") {
        val repository: UserRepository = Mockito.mock()
        val service: UserDetailsService = UserDetailsService()

        beforeEachTest {
            service.repository = repository
        }

        it("returns user found from repository") {
            val username = "Some user name"
            BDDMockito.given(repository.findByUsername(username)).willReturn(Optional.of(User(username, "pass")))

            val actual = service.loadUserByUsername(username)
            BDDMockito.then(repository).should().findByUsername(username)
            assertEquals(username, actual.username)
        }

        it("throws user not found on failing to find user") {
            val username = "Some user name"
            BDDMockito.given(repository.findByUsername(username)).willReturn(Optional.empty())

            assertThrows<UsernameNotFoundException> { service.loadUserByUsername(username) }
        }
    }
})