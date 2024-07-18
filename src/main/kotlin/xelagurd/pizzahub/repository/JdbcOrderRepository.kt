package xelagurd.pizzahub.repository

import org.springframework.asm.Type
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import xelagurd.pizzahub.dto.IngredientRef
import xelagurd.pizzahub.dto.Pizza
import xelagurd.pizzahub.dto.PizzaOrder
import java.sql.Types
import java.util.*


@Repository
class JdbcOrderRepository(val jdbcOperations: JdbcOperations) : OrderRepository {
    @Transactional
    override fun save(order: PizzaOrder): PizzaOrder {
        val pscf = PreparedStatementCreatorFactory(
            "insert into Pizza_Order "
                    + "(delivery_name, delivery_street, delivery_city, "
                    + "delivery_state, delivery_zip, cc_number, "
                    + "cc_expiration, cc_cvv, placed_at) "
                    + "values (?,?,?,?,?,?,?,?,?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        )
        pscf.setReturnGeneratedKeys(true)

        order.placedAt = Date()

        val psc = pscf.newPreparedStatementCreator(
            listOf(
                order.deliveryName,
                order.deliveryStreet,
                order.deliveryCity,
                order.deliveryState,
                order.deliveryZip,
                order.ccNumber,
                order.ccExpiration,
                order.ccCVV,
                order.placedAt
            )
        )

        val keyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)
        val orderId = keyHolder.key!!.toLong()
        order.id = orderId

        val pizzas = order.pizzas
        for ((i, pizza) in pizzas.withIndex()) {
            savePizza(orderId, i, pizza)
        }

        return order
    }

    private fun savePizza(orderId: Long, orderKey: Int, pizza: Pizza): Long {
        pizza.createdAt = Date()
        val pscf = PreparedStatementCreatorFactory(
            "insert into Pizza "
                    + "(name, created_at, pizza_order, pizza_order_key) "
                    + "values (?, ?, ?, ?)",
            Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
        )
        pscf.setReturnGeneratedKeys(true)

        val psc = pscf.newPreparedStatementCreator(
            listOf(
                pizza.name,
                pizza.createdAt,
                orderId,
                orderKey
            )
        )

        val keyHolder = GeneratedKeyHolder()
        jdbcOperations.update(psc, keyHolder)
        val pizzaId = keyHolder.key!!.toLong()
        pizza.id = pizzaId

        saveIngredientRefs(pizzaId, pizza.ingredients)

        return pizzaId
    }

    private fun saveIngredientRefs(pizzaId: Long, ingredientRefs: List<IngredientRef>) {
        for ((i, ingredientRef) in ingredientRefs.withIndex()) {
            jdbcOperations.update(
                "insert into Ingredient_Ref (ingredient, pizza, pizza_key) "
                        + "values (?, ?, ?)",
                ingredientRef.ingredient, pizzaId, i
            )
        }
    }
}