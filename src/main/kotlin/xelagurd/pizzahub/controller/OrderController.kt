package xelagurd.pizzahub.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import org.springframework.web.bind.support.SessionStatus
import xelagurd.pizzahub.repository.OrderRepository
import xelagurd.pizzahub.dto.PizzaOrder
import xelagurd.pizzahub.dto.User


@Controller
@RequestMapping("/orders")
@SessionAttributes("pizzaOrder")
class OrderController(private val orderRepository: OrderRepository) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("/current")
    fun orderForm(
        @AuthenticationPrincipal user: User,
        @ModelAttribute pizzaOrder: PizzaOrder
    ): String {
        pizzaOrder.user = user

        if (pizzaOrder.deliveryName == "") {
            pizzaOrder.deliveryName = user.fullname
        }
        if (pizzaOrder.deliveryStreet == "") {
            pizzaOrder.deliveryStreet = user.street
        }
        if (pizzaOrder.deliveryCity == "") {
            pizzaOrder.deliveryCity = user.city
        }
        if (pizzaOrder.deliveryState == "") {
            pizzaOrder.deliveryState = user.state
        }
        if (pizzaOrder.deliveryZip == "") {
            pizzaOrder.deliveryZip = user.zip
        }

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
        sessionStatus.setComplete()

        logger.info { "Order submitted: $order" }

        return "redirect:/"
    }
}