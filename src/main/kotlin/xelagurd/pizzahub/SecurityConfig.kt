package xelagurd.pizzahub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import xelagurd.pizzahub.repository.UserRepository


@Configuration
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(userRepo: UserRepository): UserDetailsService {
        return UserDetailsService { username ->
            val user = userRepo.findByUsername(username)

            user?.let {
                return@UserDetailsService it
            } ?: throw UsernameNotFoundException("User ‘$username’ not found")
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { }
            .authorizeHttpRequests {
                it.requestMatchers( "/design", "/orders").hasRole("USER")
                    .anyRequest().permitAll()
            }
            .formLogin { it.loginPage("/login").defaultSuccessUrl("/", true) }
            .logout { it.logoutSuccessUrl("/") }
            //.oauth2Login { it.loginPage("/login") }
            .csrf { it.ignoringRequestMatchers("/h2-console/**") }
            .headers { it.frameOptions { frameOptions -> frameOptions.sameOrigin() } }

        return http.build()
    }
}