package xelagurd.pizzahub.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import xelagurd.pizzahub.dto.Ingredient
import xelagurd.pizzahub.repository.IngredientRepository
import xelagurd.pizzahub.dto.Pizza
import xelagurd.pizzahub.dto.PizzaOrder


@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
class DesignPizzaController(val ingredientRepository: IngredientRepository) {
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

        pizzaOrder.addPizza(pizza)
        logger.info { "Processing pizza: $pizza" }
        return "redirect:/orders/current"
    }
}