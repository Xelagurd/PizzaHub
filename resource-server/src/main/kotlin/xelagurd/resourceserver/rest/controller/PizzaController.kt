package xelagurd.resourceserver.rest.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import xelagurd.resourceserver.dto.Pizza
import xelagurd.resourceserver.dto.PizzaOrder
import xelagurd.resourceserver.repository.OrderRepository
import xelagurd.resourceserver.repository.PizzaRepository
import kotlin.jvm.optionals.getOrNull


@RestController
@RequestMapping(path = ["/api/pizzas"], produces = ["application/json"])
@CrossOrigin(origins = ["http://localhost:8080"])
class PizzaController(private val pizzaRepository: xelagurd.resourceserver.repository.PizzaRepository, private val orderRepository: OrderRepository) {
    private val logger = KotlinLogging.logger {}

    @GetMapping(params = ["recent"])
    fun recentPizzas(
        @RequestParam(name = "size", defaultValue = "12") pageSize: Int,
        @RequestParam(name = "number", defaultValue = "0") pageNumber: Int
    ): Iterable<Pizza> {
        val page = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending())
        return pizzaRepository.findAll(page)
    }

    @GetMapping("/{id}")
    fun pizzaById(@PathVariable("id") id: Long): ResponseEntity<Pizza> {
        val pizza = pizzaRepository.findById(id).getOrNull()

        return pizza?.let {
            ResponseEntity(it, HttpStatus.OK)
        } ?: ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postPizza(@RequestBody pizza: Pizza): Pizza {
        logger.info { "Added pizza: $pizza" }
        return pizzaRepository.save(pizza)
    }

    @PutMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun putOrder(
        @PathVariable("orderId") orderId: Long,
        @RequestBody order: xelagurd.resourceserver.dto.PizzaOrder
    ): xelagurd.resourceserver.dto.PizzaOrder {
        order.id = orderId

        logger.info { "Changed order: $order" }
        return orderRepository.save(order)
    }

    @PatchMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun patchOrder(
        @PathVariable("orderId") orderId: Long,
        @RequestBody patch: xelagurd.resourceserver.dto.PizzaOrder
    ): xelagurd.resourceserver.dto.PizzaOrder {
        val order = orderRepository.findById(orderId).get()
        if (patch.deliveryName.isNotBlank()) {
            order.deliveryName = patch.deliveryName
        }
        if (patch.deliveryStreet.isNotBlank()) {
            order.deliveryStreet = patch.deliveryStreet
        }
        if (patch.deliveryCity.isNotBlank()) {
            order.deliveryCity = patch.deliveryCity
        }
        if (patch.deliveryState.isNotBlank()) {
            order.deliveryState = patch.deliveryState
        }
        if (patch.deliveryZip.isNotBlank()) {
            order.deliveryZip = patch.deliveryZip
        }
        if (patch.ccNumber.isNotBlank()) {
            order.ccNumber = patch.ccNumber
        }
        if (patch.ccExpiration.isNotBlank()) {
            order.ccExpiration = patch.ccExpiration
        }
        if (patch.ccCVV.isNotBlank()) {
            order.ccCVV = patch.ccCVV
        }

        logger.info { "Changed order: $order" }
        return orderRepository.save(order)
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable("orderId") orderId: Long) {
        try {
            orderRepository.deleteById(orderId)
            logger.info { "Deleted order with id: $orderId" }
        } catch (e: EmptyResultDataAccessException) {
            logger.warn { "Can't delete order with id: $orderId" }
        }
    }
}