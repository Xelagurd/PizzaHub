package xelagurd.resourceserver

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import xelagurd.resourceserver.dto.Ingredient
import xelagurd.resourceserver.dto.Pizza
import xelagurd.resourceserver.dto.User
import xelagurd.resourceserver.repository.IngredientRepository
import xelagurd.resourceserver.repository.PizzaRepository
import xelagurd.resourceserver.repository.UserRepository


@Configuration
@Profile("!prod")
class DevelopmentConfig {

    @Bean
    fun dataLoader(
        ingredientRepository: IngredientRepository,
        pizzaRepository: xelagurd.resourceserver.repository.PizzaRepository,
        userRepository: xelagurd.resourceserver.repository.UserRepository,
        passwordEncoder: PasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            val flourTortilla = xelagurd.resourceserver.dto.Ingredient(
                "FLTO",
                "Flour Tortilla",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.WRAP
            )
            val cornTortilla = xelagurd.resourceserver.dto.Ingredient(
                "COTO",
                "Corn Tortilla",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.WRAP
            )
            val groundBeef = xelagurd.resourceserver.dto.Ingredient(
                "GRBF",
                "Ground Beef",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.PROTEIN
            )
            val carnitas = xelagurd.resourceserver.dto.Ingredient(
                "CARN",
                "Carnitas",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.PROTEIN
            )
            val tomatoes = xelagurd.resourceserver.dto.Ingredient(
                "TMTO",
                "Diced Tomatoes",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.VEGGIES
            )
            val lettuce = xelagurd.resourceserver.dto.Ingredient(
                "LETC",
                "Lettuce",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.VEGGIES
            )
            val cheddar = xelagurd.resourceserver.dto.Ingredient(
                "CHED",
                "Cheddar",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.CHEESE
            )
            val jack = xelagurd.resourceserver.dto.Ingredient(
                "JACK",
                "Monterrey Jack",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.CHEESE
            )
            val salsa = xelagurd.resourceserver.dto.Ingredient(
                "SLSA",
                "Salsa",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.SAUCE
            )
            val sourCream = xelagurd.resourceserver.dto.Ingredient(
                "SRCR",
                "Sour Cream",
                xelagurd.resourceserver.dto.Ingredient.IngredientType.SAUCE
            )
            ingredientRepository.save(flourTortilla)
            ingredientRepository.save(cornTortilla)
            ingredientRepository.save(groundBeef)
            ingredientRepository.save(carnitas)
            ingredientRepository.save(tomatoes)
            ingredientRepository.save(lettuce)
            ingredientRepository.save(cheddar)
            ingredientRepository.save(jack)
            ingredientRepository.save(salsa)
            ingredientRepository.save(sourCream)

            val pizza1 = Pizza(
                null, "Carnivore", arrayListOf(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar
                ), null
            )
            val pizza2 = Pizza(
                null, "Bovine Bounty", arrayListOf(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream
                ), null
            )
            val pizza3 = Pizza(
                null, "Veg-Out", arrayListOf(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa
                ), null
            )
            pizzaRepository.save(pizza1)
            pizzaRepository.save(pizza2)
            pizzaRepository.save(pizza3)

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