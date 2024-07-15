package xelagurd.pizzahub

data class PizzaOrder(
    var deliveryName: String = "",
    var deliveryStreet: String = "",
    var deliveryCity: String = "",
    var deliveryState: String = "",
    var deliveryZip: String = "",
    var ccNumber: String = "",
    var ccExpiration: String = "",
    var ccCVV: String = "",
    val pizzas: ArrayList<Pizza> = arrayListOf()
) {
    fun addPizza(pizza: Pizza) {
        this.pizzas.add(pizza)
    }
}