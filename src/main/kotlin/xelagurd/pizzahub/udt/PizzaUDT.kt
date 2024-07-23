package xelagurd.pizzahub.udt

import org.springframework.data.cassandra.core.mapping.UserDefinedType

@UserDefinedType("pizza")
class PizzaUDT(
    var name: String = "",
    var ingredients: MutableList<IngredientUDT> = arrayListOf()
) {
    override fun toString(): String {
        return "PizzaUDT(name=$name, ingredients=$ingredients)"
    }
}