package xelagurd.pizzahub.dto

import com.datastax.oss.driver.api.core.uuid.Uuids
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.CreditCardNumber
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import xelagurd.pizzahub.udt.PizzaUDT
import xelagurd.pizzahub.udt.utils.PizzaUDRUtils
import java.util.*

@Table("orders")
class PizzaOrder(
    @field:PrimaryKey
    var id: UUID? = Uuids.timeBased(),

    @field:NotBlank(message = "Delivery name is required")
    var deliveryName: String = "",

    @field:NotBlank(message = "Street is required")
    var deliveryStreet: String = "",

    @field:NotBlank(message = "City is required")
    var deliveryCity: String = "",

    @field:NotBlank(message = "State is required")
    @field:Size(max = 2, message = "Delivery state must be no more 2 characters long")
    var deliveryState: String = "",

    @field:NotBlank(message = "Zip code is required")
    @field:Size(max = 10, message = "Delivery zip must be no more 10 characters long")
    var deliveryZip: String = "",

    @field:CreditCardNumber(message = "Not a valid credit card number")
    var ccNumber: String = "",

    @field:Pattern(regexp = "^(0[1-9]|1[0-2])/([2-9][0-9])$", message = "Must be formatted MM/YY")
    var ccExpiration: String = "",

    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    var ccCVV: String = "",

    @field:Column("pizzas")
    var pizzas: MutableList<PizzaUDT> = arrayListOf(),

    var placedAt: Date = Date()
) {
    fun addPizza(pizza: Pizza) {
        this.pizzas.add(PizzaUDRUtils.toPizzaUDT(pizza))
    }

    override fun toString(): String {
        return "PizzaOrder(deliveryName=$deliveryName, pizzas=$pizzas)"
    }
}