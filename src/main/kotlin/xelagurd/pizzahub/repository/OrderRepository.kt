package xelagurd.pizzahub.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.PizzaOrder
import xelagurd.pizzahub.dto.User

interface OrderRepository : CrudRepository<PizzaOrder, Long> {
    fun findByUserOrderByPlacedAtDesc(user: User, pageable: Pageable): List<PizzaOrder>
}