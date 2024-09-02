package xelagurd.resourceserver.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.resourceserver.dto.Ingredient

interface IngredientRepository : CrudRepository<xelagurd.resourceserver.dto.Ingredient, String>