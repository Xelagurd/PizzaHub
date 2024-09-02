package xelagurd.pizzahub.rest.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.repository.IngredientRepository

@RestController
@RequestMapping(path = ["/api/ingredients"], produces = ["application/json"])
@CrossOrigin(origins = ["http://localhost:8080"])
class IngredientController(private val ingredientRepository: IngredientRepository) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun allIngredients(): Iterable<Ingredient> {
        return ingredientRepository.findAll()
    }

    @PostMapping
    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveIngredient(@RequestBody ingredient: Ingredient): Ingredient {
        logger.info { "Added ingredient: $ingredient" }
        return ingredientRepository.save(ingredient)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteIngredient(@PathVariable("id") ingredientId: String) {
        try {
            ingredientRepository.deleteById(ingredientId)
            logger.info { "Deleted ingredient with id: $ingredientId" }
        } catch (e: EmptyResultDataAccessException) {
            logger.warn { "Can't delete ingredient with id: $ingredientId" }
        }
    }
}