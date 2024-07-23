package xelagurd.pizzahub.dto.utils

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import xelagurd.pizzahub.repository.IngredientRepository
import xelagurd.pizzahub.udt.IngredientUDT


@Component
class IngredientByIdConverter(val ingredientRepository: IngredientRepository) : Converter<String, IngredientUDT?> {
    override fun convert(id: String): IngredientUDT? {
        val ingredient = ingredientRepository.findById(id)
        if (ingredient.isEmpty) {
            return null
        }

        with(ingredient.get()) {
            return IngredientUDT(name, type)
        }
    }
}