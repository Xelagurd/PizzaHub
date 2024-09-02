package xelagurd.resourceserver.rest

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import xelagurd.resourceserver.dto.Ingredient


@Component
class RestClient(private val restTemplate: RestTemplate) {
    private val logger = KotlinLogging.logger {}

    @Value("\${spring.data.rest.base-path}")
    private var restBasePath: String = ""

    fun getIngredientById(ingredientId: String): xelagurd.resourceserver.dto.Ingredient? {
        return restTemplate.getForObject(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            xelagurd.resourceserver.dto.Ingredient::class.java,
            ingredientId
        )
    }

    fun createIngredient(ingredient: xelagurd.resourceserver.dto.Ingredient): xelagurd.resourceserver.dto.Ingredient? {
        return restTemplate.postForObject(
            "http://localhost:8080$restBasePath/ingredients",
            ingredient,
            xelagurd.resourceserver.dto.Ingredient::class.java
        )
    }

    fun updateIngredient(ingredient: xelagurd.resourceserver.dto.Ingredient) {
        restTemplate.put(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            ingredient,
            ingredient.id
        )
    }

    fun deleteIngredient(ingredient: xelagurd.resourceserver.dto.Ingredient) {
        restTemplate.delete(
            "http://localhost:8080$restBasePath/ingredients/{id}",
            ingredient.id
        )
    }
}