package com.valr

import com.valr.security.UserService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import org.springframework.security.config.annotation.web.builders.HttpSecurity

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
@EnableWebSecurity
class App {

    @Autowired
    private lateinit var userDetailsService: com.valr.security.UserDetailsService

    @Autowired
    private lateinit var userService: UserService

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(App::class.java, *args)
        }
    }

    @Bean
    fun formLoginFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize("/{currencyPair}/orderbook", permitAll)
            }

            passwordManagement { passwordEncoder() }
            formLogin { }
            logout { }
            csrf { disable() }
        }

        http.userDetailsService(userDetailsService)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): Pbkdf2PasswordEncoder {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    }


    @PostConstruct
    fun startupOrInit() {
        userService.addUsers()
    }

}