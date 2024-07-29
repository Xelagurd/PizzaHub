package xelagurd.pizzahub.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import xelagurd.pizzahub.controller.service.AdminService

@Controller
@RequestMapping("/admin")
class AdminController(private val adminService: AdminService) {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun showAdminPage(): String {
        return "admin"
    }

    @PostMapping("/deleteOrders")
    fun deleteAllOrders(): String {
        adminService.deleteAllOrders()

        logger.info { "Deleted all orders" }

        return "redirect:/admin"
    }
}