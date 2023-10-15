package com.valr.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService {

    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
    private val username = "user"

    @Autowired
    lateinit var repository: UserRepository


    fun addUsers() {
        logger.info("Adding default users ")

        val user: Optional<User> = repository.findByUsername(username)
        if (user.isEmpty) {
            logger.info("User user absent. Saving the same")
            repository.save(User(username,"3a2c1a4bb957165a04f4ab7ad462656af092942153b75633e1b77af4cb3fa5b85216f09da3b8e678d6b957399ba9c2dc" ))
        }

    }
}