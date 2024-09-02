package xelagurd.resourceserver.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import xelagurd.resourceserver.dto.PizzaOrder
import xelagurd.resourceserver.dto.User

interface OrderRepository : CrudRepository<xelagurd.resourceserver.dto.PizzaOrder, Long> {
    fun findByUserOrderByPlacedAtDesc(user: User, pageable: Pageable): List<xelagurd.resourceserver.dto.PizzaOrder>
}