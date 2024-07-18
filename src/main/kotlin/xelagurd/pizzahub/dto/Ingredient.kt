package xelagurd.pizzahub.dto

import jakarta.validation.constraints.Size
import org.springframework.data.annotation.Id

data class Ingredient(
    @field:Id
    val id: String,

    @field:Size(max = 25, message = "Name must be no more 25 characters long")
    val name: String,

    val type: IngredientType
) {
    enum class IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}