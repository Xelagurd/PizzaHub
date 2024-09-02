package xelagurd.resourceserver.web.service

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import xelagurd.resourceserver.repository.OrderRepository

@Service
class AdminService(private val orderRepository: OrderRepository) {

    @PreAuthorize("hasRole('ADMIN')")
    fun deleteAllOrders() {
        orderRepository.deleteAll()
    }
}