package xelagurd.pizzahub

import jakarta.validation.constraints.Size

data class Pizza(
    @field:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: ArrayList<Ingredient> = arrayListOf()
)