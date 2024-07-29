package xelagurd.pizzahub.controller.service

import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service
import xelagurd.pizzahub.dto.PizzaOrder
import xelagurd.pizzahub.repository.OrderRepository
import kotlin.jvm.optionals.getOrNull

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @PostAuthorize("hasRole('ADMIN') || returnObject.user.username == authentication.name")
    fun getOrder(id: Long): PizzaOrder? {
        return orderRepository.findById(id).getOrNull()
    }
}