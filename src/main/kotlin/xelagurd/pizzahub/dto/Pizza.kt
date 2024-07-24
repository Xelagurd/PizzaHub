package xelagurd.pizzahub.dto

import jakarta.validation.constraints.Size
import java.util.*

class Pizza(
    @field:Size(min = 5, max = 50, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: MutableList<Ingredient> = arrayListOf(),

    var createdAt: Date = Date()
) {
    fun addIngredient(ingredient: Ingredient) {
        this.ingredients.add(ingredient)
    }

    override fun toString(): String {
        return "Pizza(name=$name, ingredients=$ingredients)"
    }
}