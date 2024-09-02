package xelagurd.resourceserver.web.service

import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service
import xelagurd.resourceserver.dto.PizzaOrder
import xelagurd.resourceserver.repository.OrderRepository
import kotlin.jvm.optionals.getOrNull

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @PostAuthorize("hasRole('ADMIN') || returnObject.user.username == authentication.name")
    fun getOrder(id: Long): xelagurd.resourceserver.dto.PizzaOrder? {
        return orderRepository.findById(id).getOrNull()
    }
}