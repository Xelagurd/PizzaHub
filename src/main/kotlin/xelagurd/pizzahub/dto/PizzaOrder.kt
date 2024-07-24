package xelagurd.pizzahub.dto

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*

@Entity
class PizzaOrder(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

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

    @field:OneToMany(cascade = [CascadeType.ALL])
    var pizzas: MutableList<Pizza> = arrayListOf(),

    var placedAt: Date = Date()
) {
    fun addPizza(pizza: Pizza) {
        this.pizzas.add(pizza)
    }

    override fun toString(): String {
        return "PizzaOrder(deliveryName=$deliveryName, pizzas=$pizzas)"
    }
}