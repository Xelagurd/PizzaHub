package xelagurd.pizzahub.repository

import xelagurd.pizzahub.dto.PizzaOrder

interface OrderRepository {
    fun save(order: PizzaOrder): PizzaOrder
}