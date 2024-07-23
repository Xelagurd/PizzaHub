package xelagurd.pizzahub.udt

import org.springframework.data.cassandra.core.mapping.UserDefinedType
import xelagurd.pizzahub.dto.Ingredient

@UserDefinedType("ingredient")
class IngredientUDT(
    var name: String = "",
    var type: Ingredient.IngredientType? = null
) {
    override fun toString(): String {
        return "IngredientUDT(name=$name, type=$type)"
    }
}