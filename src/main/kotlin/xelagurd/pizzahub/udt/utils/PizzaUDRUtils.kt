package xelagurd.pizzahub.udt.utils

import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.dto.Pizza
import xelagurd.pizzahub.udt.IngredientUDT
import xelagurd.pizzahub.udt.PizzaUDT


object PizzaUDRUtils {
    fun toPizzaUDT(pizza: Pizza): PizzaUDT {
        return PizzaUDT(pizza.name, pizza.ingredients)
    }

    fun toIngredientUDTs(ingredients: List<Ingredient>): List<IngredientUDT> {
        return ingredients.map { toIngredientUDT(it) }
    }

    fun toIngredientUDT(ingredient: Ingredient): IngredientUDT {
        return IngredientUDT(ingredient.name, ingredient.type)
    }
}