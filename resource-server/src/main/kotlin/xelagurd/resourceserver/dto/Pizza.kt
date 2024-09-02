package xelagurd.resourceserver.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.PrePersist
import jakarta.validation.constraints.Size
import java.util.*


@Entity(name = "pizzas")
class Pizza(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:Size(min = 5, max = 50, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    @field:ManyToMany
    var ingredients: MutableList<xelagurd.resourceserver.dto.Ingredient> = arrayListOf(),

    var createdAt: Date? = null
) {
    fun addIngredient(ingredient: xelagurd.resourceserver.dto.Ingredient) {
        this.ingredients.add(ingredient)
    }

    @PrePersist
    fun createdAt() {
        this.createdAt = Date()
    }

    override fun toString(): String {
        return "Pizza(name=$name, ingredients=$ingredients)"
    }
}