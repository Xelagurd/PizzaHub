package xelagurd.pizzahub.dto

import com.datastax.oss.driver.api.core.uuid.Uuids
import jakarta.validation.constraints.Size
import org.springframework.data.cassandra.core.cql.Ordering
import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table
import xelagurd.pizzahub.udt.IngredientUDT
import xelagurd.pizzahub.udt.utils.PizzaUDRUtils
import java.util.*

@Table("pizzas")
class Pizza(
    @field:PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
    var id: UUID? = Uuids.timeBased(),

    @field:Size(min = 5, max = 50, message = "Name must be at least 5 characters long")
    var name: String = "",

    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    @field:Column("ingredients")
    var ingredients: MutableList<IngredientUDT> = arrayListOf(),

    @field:PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    var createdAt: Date = Date()
) {
    fun addIngredient(ingredient: Ingredient) {
        this.ingredients.add(PizzaUDRUtils.toIngredientUDT(ingredient))
    }

    override fun toString(): String {
        return "Pizza(name=$name, ingredients=$ingredients)"
    }
}