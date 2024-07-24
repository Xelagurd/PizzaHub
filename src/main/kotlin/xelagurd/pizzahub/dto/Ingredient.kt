package xelagurd.pizzahub.dto

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Size

@Entity
class Ingredient(
    @field:Id
    var id: String? = null,

    @field:Size(max = 25, message = "Name must be no more 25 characters long")
    var name: String = "",

    var type: IngredientType? = null
) {
    enum class IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}