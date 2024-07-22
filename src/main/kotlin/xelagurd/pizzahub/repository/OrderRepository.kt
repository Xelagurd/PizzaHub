package xelagurd.pizzahub.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.PizzaOrder
import java.util.*

interface OrderRepository : CrudRepository<PizzaOrder, Long> {
    fun findByDeliveryZip(deliveryZip: String): List<PizzaOrder>

    fun readOrdersByDeliveryZipAndPlacedAtBetween(
        deliveryZip: String,
        startDate: Date,
        endDate: Date
    ): List<PizzaOrder>

    fun findByDeliveryStreetAndDeliveryCityAllIgnoreCase(
        deliveryStreet: String,
        deliveryCity: String
    ): List<PizzaOrder>

    fun findByDeliveryCityOrderByDeliveryStreet(city: String): List<PizzaOrder>

    @Query("select o from PizzaOrder o where o.deliveryCity='Seattle'")
    fun readOrdersDeliveredInSeattle(): List<PizzaOrder>
}