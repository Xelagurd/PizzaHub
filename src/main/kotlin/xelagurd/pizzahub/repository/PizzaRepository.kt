package xelagurd.pizzahub.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.Pizza

interface PizzaRepository : CrudRepository<Pizza, Long>