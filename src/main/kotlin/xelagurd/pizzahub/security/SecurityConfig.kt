package xelagurd.pizzahub.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(encoder: PasswordEncoder): InMemoryUserDetailsManager {
        val usersList = mutableListOf<User>()
        usersList.add(
            User(
                "buzz", encoder.encode("password"),
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
        )
        usersList.add(
            User(
                "woody", encoder.encode("password"),
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
        )
        usersList.add(
            User(
                "abc", encoder.encode("123"),
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
        )
        return InMemoryUserDetailsManager(usersList.toList())
    }
}