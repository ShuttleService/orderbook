package com.valr.security


import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

import java.util.Optional

import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Captor
    lateinit var captor: ArgumentCaptor<User>
    private val repository: UserRepository = Mockito.mock()
    @InjectMocks
    private lateinit var service: UserService

    @Test
    fun addsUsers() {

        BDDMockito.given(repository.findByUsername("user")).willReturn(Optional.empty())

        service.addUsers()

        BDDMockito.then(repository).should().findByUsername("user")
        BDDMockito.then(repository).should().save(captor.capture())

        val user = captor.value
        assertEquals("user", user.username)
        assertNotNull(user.password)
    }

    @Test
    fun onlyAddsUsersOnce() {

        BDDMockito.given(repository.findByUsername("user")).willReturn(Optional.of(User("", "")))

        service.addUsers()

        BDDMockito.then(repository).should(Mockito.never()).save(Mockito.any())
    }
}
