package xelagurd.resourceserver.web.utils

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import xelagurd.resourceserver.dto.Ingredient
import xelagurd.resourceserver.repository.IngredientRepository


@Component
class IngredientByIdConverter(val ingredientRepository: IngredientRepository) : Converter<String, xelagurd.resourceserver.dto.Ingredient?> {
    override fun convert(id: String): xelagurd.resourceserver.dto.Ingredient? {
        return ingredientRepository.findById(id).orElse(null)
    }
}