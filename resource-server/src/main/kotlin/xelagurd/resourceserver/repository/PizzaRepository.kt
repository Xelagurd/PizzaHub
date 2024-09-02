package xelagurd.resourceserver.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import xelagurd.resourceserver.dto.Pizza

interface PizzaRepository : CrudRepository<Pizza, Long> {
    fun findAll(pageable: Pageable): Iterable<Pizza>
}