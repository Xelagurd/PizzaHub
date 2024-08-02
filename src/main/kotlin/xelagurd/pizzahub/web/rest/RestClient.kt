package xelagurd.pizzahub.web.rest

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import xelagurd.pizzahub.dto.Ingredient


@Component
class RestClient(private val restTemplate: RestTemplate) {
    private val logger = KotlinLogging.logger {}

    @Value("\${spring.data.rest.base-path}")
    private var restBasePath: String = ""

    fun getIngredientById(ingredientId: String): Ingredient? {
        return restTemplate.getForObject(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            Ingredient::class.java,
            ingredientId
        )
    }

    fun createIngredient(ingredient: Ingredient): Ingredient? {
        return restTemplate.postForObject(
            "http://localhost:8080$restBasePath/ingredients",
            ingredient,
            Ingredient::class.java
        )
    }

    fun updateIngredient(ingredient: Ingredient) {
        restTemplate.put(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            ingredient,
            ingredient.id
        )
    }

    fun deleteIngredient(ingredient: Ingredient) {
        restTemplate.delete(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            ingredient.id
        )
    }
}