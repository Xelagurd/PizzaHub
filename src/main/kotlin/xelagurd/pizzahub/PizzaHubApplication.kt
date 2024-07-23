package xelagurd.pizzahub

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.repository.IngredientRepository


@SpringBootApplication
class PizzaHubApplication {
    @Bean
    fun dataLoader(repo: IngredientRepository): CommandLineRunner {
        return CommandLineRunner {
            repo.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.IngredientType.WRAP))
            repo.save(Ingredient("COTO", "Corn Tortilla", Ingredient.IngredientType.WRAP))
            repo.save(Ingredient("GRBF", "Ground Beef", Ingredient.IngredientType.PROTEIN))
            repo.save(Ingredient("CARN", "Carnitas", Ingredient.IngredientType.PROTEIN))
            repo.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.IngredientType.VEGGIES))
            repo.save(Ingredient("LETC", "Lettuce", Ingredient.IngredientType.VEGGIES))
            repo.save(Ingredient("CHED", "Cheddar", Ingredient.IngredientType.CHEESE))
            repo.save(Ingredient("JACK", "Monterrey Jack", Ingredient.IngredientType.CHEESE))
            repo.save(Ingredient("SLSA", "Salsa", Ingredient.IngredientType.SAUCE))
            repo.save(Ingredient("SRCR", "Sour Cream", Ingredient.IngredientType.SAUCE))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<PizzaHubApplication>(*args)
}
