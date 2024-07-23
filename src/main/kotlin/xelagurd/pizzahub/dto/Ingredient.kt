package xelagurd.pizzahub.dto

import jakarta.validation.constraints.Size
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("ingredients")
class Ingredient(
    @field:PrimaryKey
    var id: String? = null,

    @field:Size(max = 25, message = "Name must be no more 25 characters long")
    var name: String = "",

    var type: IngredientType? = null
) {
    enum class IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}