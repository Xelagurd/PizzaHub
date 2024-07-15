package xelagurd.pizzahub

data class Ingredient(val id: String, val name: String, val type: IngredientType) {
    enum class IngredientType {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}