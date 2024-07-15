package xelagurd.pizzahub

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


@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
class DesignPizzaController {
    private val logger = KotlinLogging.logger {}

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        val ingredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Ingredient.IngredientType.WRAP),
            Ingredient("COTO", "Corn Tortilla", Ingredient.IngredientType.WRAP),
            Ingredient("GRBF", "Ground Beef", Ingredient.IngredientType.PROTEIN),
            Ingredient("CARN", "Carnitas", Ingredient.IngredientType.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Ingredient.IngredientType.VEGGIES),
            Ingredient("LETC", "Lettuce", Ingredient.IngredientType.VEGGIES),
            Ingredient("CHED", "Cheddar", Ingredient.IngredientType.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Ingredient.IngredientType.CHEESE),
            Ingredient("SLSA", "Salsa", Ingredient.IngredientType.SAUCE),
            Ingredient("SRCR", "Sour Cream", Ingredient.IngredientType.SAUCE)
        )

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