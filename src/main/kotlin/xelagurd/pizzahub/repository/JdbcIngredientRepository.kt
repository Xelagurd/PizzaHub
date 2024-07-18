package xelagurd.pizzahub.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import xelagurd.pizzahub.dto.Ingredient
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


@Repository
class JdbcIngredientRepository(val jdbcTemplate: JdbcTemplate): IngredientRepository {
    override fun findAll(): Iterable<Ingredient> {
        return jdbcTemplate.query(
            "select id, name, type from Ingredient",
            this::mapRowToIngredient
        )
    }

    override fun findById(id: String): Optional<Ingredient> {
        val results = jdbcTemplate.query(
            "select id, name, type from Ingredient where id=?",
            this::mapRowToIngredient,
            id
        )

        return if (results.size == 0) Optional.empty() else Optional.of(results[0])
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbcTemplate.update(
            "insert into Ingredient (id, name, type) values (?, ?, ?)",
            ingredient.id,
            ingredient.name,
            ingredient.type.toString()
        )

        return ingredient
    }

    @Throws(SQLException::class)
    private fun mapRowToIngredient(row: ResultSet, rowNum: Int): Ingredient {
        return Ingredient(
            row.getString("id"),
            row.getString("name"),
            Ingredient.IngredientType.valueOf(row.getString("type"))
        )
    }
}