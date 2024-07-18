package xelagurd.pizzahub.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.PizzaOrder

interface OrderRepository : CrudRepository<PizzaOrder, Long>