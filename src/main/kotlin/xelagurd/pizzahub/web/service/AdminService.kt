package xelagurd.pizzahub.web.service

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import xelagurd.pizzahub.repository.OrderRepository

@Service
class AdminService(private val orderRepository: OrderRepository) {

    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAllOrders() {
        orderRepository.deleteAll()
    }
}