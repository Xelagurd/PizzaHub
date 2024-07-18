package xelagurd.pizzahub.repository

import xelagurd.pizzahub.dto.Ingredient
import java.util.Optional

interface IngredientRepository {
    fun findAll(): Iterable<Ingredient>
    fun findById(id: String): Optional<Ingredient>
    fun save(ingredient: Ingredient): Ingredient
}