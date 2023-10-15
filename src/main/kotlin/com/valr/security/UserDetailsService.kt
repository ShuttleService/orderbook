package com.valr.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService : UserDetailsService {
    private val logger: Logger = LoggerFactory.getLogger(UserDetailsService::class.java)

    @Autowired
    lateinit var repository: UserRepository

    override fun loadUserByUsername(username: String?): User {
        if (username == null) {
            logger.info("username is null")
            throw UsernameNotFoundException("username is null")
        }
        logger.info("Finding user by username $username")
        return repository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("user with username $username not found") }
    }
}