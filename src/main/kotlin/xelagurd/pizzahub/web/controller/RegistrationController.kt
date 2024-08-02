package xelagurd.pizzahub.web.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import xelagurd.pizzahub.dto.RegistrationForm
import xelagurd.pizzahub.repository.UserRepository

@Controller
@RequestMapping("/register")
class RegistrationController(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun registerForm(): String {
        return "registration"
    }

    @PostMapping
    fun processRegistration(form: RegistrationForm): String {
        val user = form.toUser(passwordEncoder)

        userRepo.save(user)
        logger.info { "New user saved: $user" }

        return "redirect:/login"
    }
}