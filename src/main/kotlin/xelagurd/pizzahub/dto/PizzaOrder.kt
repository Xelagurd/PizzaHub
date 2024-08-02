package xelagurd.pizzahub.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.CreditCardNumber
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*


@Entity(name = "orders")
@RestResource(rel="orders", path="orders")
class PizzaOrder(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:ManyToOne
    var user: User? = null,

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

    @field:OneToMany
    var pizzas: MutableList<Pizza> = arrayListOf(),

    var placedAt: Date? = null
) {
    fun addPizza(pizza: Pizza) {
        this.pizzas.add(pizza)
    }

    @PrePersist
    fun placedAt() {
        this.placedAt = Date()
    }

    override fun toString(): String {
        return "PizzaOrder(user=$user, pizzas=$pizzas)"
    }
}