package xelagurd.pizzahub

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.dto.User
import xelagurd.pizzahub.repository.IngredientRepository
import xelagurd.pizzahub.repository.UserRepository

@Configuration
@Profile("!prod")
class DevelopmentConfig {

    @Bean
    fun dataLoader(
        ingredientRepository: IngredientRepository,
        userRepository: UserRepository,
        passwordEncoder: PasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            ingredientRepository.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.IngredientType.WRAP))
            ingredientRepository.save(Ingredient("COTO", "Corn Tortilla", Ingredient.IngredientType.WRAP))
            ingredientRepository.save(Ingredient("GRBF", "Ground Beef", Ingredient.IngredientType.PROTEIN))
            ingredientRepository.save(Ingredient("CARN", "Carnitas", Ingredient.IngredientType.PROTEIN))
            ingredientRepository.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.IngredientType.VEGGIES))
            ingredientRepository.save(Ingredient("LETC", "Lettuce", Ingredient.IngredientType.VEGGIES))
            ingredientRepository.save(Ingredient("CHED", "Cheddar", Ingredient.IngredientType.CHEESE))
            ingredientRepository.save(Ingredient("JACK", "Monterrey Jack", Ingredient.IngredientType.CHEESE))
            ingredientRepository.save(Ingredient("SLSA", "Salsa", Ingredient.IngredientType.SAUCE))
            ingredientRepository.save(Ingredient("SRCR", "Sour Cream", Ingredient.IngredientType.SAUCE))
            ingredientRepository.save(Ingredient("SRCR", "Sour Cream", Ingredient.IngredientType.SAUCE))

            userRepository.save(
                User(
                    null,
                    "abc",
                    passwordEncoder.encode("123"),
                    "sadsa",
                    "adasdds",
                    "sdassd",
                    "ad",
                    "asdasd",
                    "asdsad"
                )
            )
        }
    }
}