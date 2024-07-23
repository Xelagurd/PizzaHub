package xelagurd.pizzahub.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.PizzaOrder
import java.util.*

interface OrderRepository : CrudRepository<PizzaOrder, UUID>