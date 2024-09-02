package xelagurd.pizzahub.web.utils

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.repository.IngredientRepository


@Component
class IngredientByIdConverter(val ingredientRepository: IngredientRepository) : Converter<String, Ingredient?> {
    override fun convert(id: String): Ingredient? {
        return ingredientRepository.findById(id).orElse(null)
    }
}