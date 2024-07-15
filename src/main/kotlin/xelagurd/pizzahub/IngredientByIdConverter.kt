package xelagurd.pizzahub

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


@Component
class IngredientByIdConverter : Converter<String, Ingredient?> {
    private val ingredientMap = HashMap<String, Ingredient>()

    init {
        ingredientMap["FLTO"] = Ingredient("FLTO", "Flour Tortilla", Ingredient.IngredientType.WRAP)
        ingredientMap["COTO"] = Ingredient("COTO", "Corn Tortilla", Ingredient.IngredientType.WRAP)
        ingredientMap["GRBF"] = Ingredient("GRBF", "Ground Beef", Ingredient.IngredientType.PROTEIN)
        ingredientMap["CARN"] = Ingredient("CARN", "Carnitas", Ingredient.IngredientType.PROTEIN)
        ingredientMap["TMTO"] = Ingredient("TMTO", "Diced Tomatoes", Ingredient.IngredientType.VEGGIES)
        ingredientMap["LETC"] = Ingredient("LETC", "Lettuce", Ingredient.IngredientType.VEGGIES)
        ingredientMap["CHED"] = Ingredient("CHED", "Cheddar", Ingredient.IngredientType.CHEESE)
        ingredientMap["JACK"] = Ingredient("JACK", "Monterrey Jack", Ingredient.IngredientType.CHEESE)
        ingredientMap["SLSA"] = Ingredient("SLSA", "Salsa", Ingredient.IngredientType.SAUCE)
        ingredientMap["SRCR"] = Ingredient("SRCR", "Sour Cream", Ingredient.IngredientType.SAUCE)
    }

    override fun convert(id: String): Ingredient? {
        return ingredientMap[id]
    }
}