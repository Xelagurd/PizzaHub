package xelagurd.pizzahub.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import xelagurd.pizzahub.repository.OrderRepository
import xelagurd.pizzahub.dto.PizzaOrder


@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
class OrderController(val orderRepository: OrderRepository) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/current")
    fun orderForm(): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid order: PizzaOrder,
        errors: Errors,
        sessionStatus: SessionStatus
    ): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        orderRepository.save(order)
        logger.info { "Order submitted: $order" }
        sessionStatus.setComplete()
        return "redirect:/"
    }
}