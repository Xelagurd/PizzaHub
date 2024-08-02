package xelagurd.pizzahub.web.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.dto.Pizza
import xelagurd.pizzahub.dto.PizzaOrder
import xelagurd.pizzahub.dto.User
import xelagurd.pizzahub.repository.IngredientRepository
import xelagurd.pizzahub.repository.PizzaRepository


@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
class DesignPizzaController(
    private val ingredientRepository: IngredientRepository,
    private val pizzaRepository: PizzaRepository
) {
    private val logger = KotlinLogging.logger {}

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = ingredientRepository.findAll()

        for (type in Ingredient.IngredientType.entries) {
            model.addAttribute(
                type.toString().lowercase(),
                ingredients.filter { it.type == type }
            )
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    fun pizzaOrder(): PizzaOrder {
        return PizzaOrder()
    }

    @ModelAttribute(name = "pizza")
    fun pizza(): Pizza {
        return Pizza()
    }

    @ModelAttribute(name = "user")
    fun user(@AuthenticationPrincipal user: User): User {
        return user
    }

    @GetMapping
    fun showDesignForm(): String {
        return "design"
    }

    @PostMapping
    fun processPizza(
        @Valid pizza: Pizza,
        errors: Errors,
        @ModelAttribute pizzaOrder: PizzaOrder
    ): String {
        if (errors.hasErrors()) {
            return "design"
        }

        val savedPizza = pizzaRepository.save(pizza)
        pizzaOrder.addPizza(savedPizza)

        logger.info { "Added pizza: $pizza" }

        return "redirect:/orders/current"
    }
}