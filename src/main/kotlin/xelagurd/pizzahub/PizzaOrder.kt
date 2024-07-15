package xelagurd.pizzahub

data class PizzaOrder(
    val deliveryName: String = "",
    val deliveryStreet: String = "",
    val deliveryCity: String = "",
    val deliveryState: String = "",
    val deliveryZip: String = "",
    val ccNumber: String = "",
    val ccExpiration: String = "",
    val ccCVV: String = "",
    val pizzas: ArrayList<Pizza> = ArrayList()
) {
    fun addPizza(pizza: Pizza) {
        this.pizzas.add(pizza)
    }
}