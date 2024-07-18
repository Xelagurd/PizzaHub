package xelagurd.pizzahub.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.Ingredient

interface IngredientRepository : CrudRepository<Ingredient, String>