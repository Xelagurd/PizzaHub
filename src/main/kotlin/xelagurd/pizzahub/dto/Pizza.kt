package xelagurd.pizzahub.dto

import jakarta.validation.constraints.Size
import java.util.*
import kotlin.collections.ArrayList

data class Pizza(
    var id: Long? = null,

    @field:Size(min = 5, max = 50, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: ArrayList<IngredientRef> = arrayListOf(),

    var createdAt: Date = Date()
)