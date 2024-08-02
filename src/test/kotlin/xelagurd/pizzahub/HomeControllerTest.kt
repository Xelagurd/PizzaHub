package xelagurd.pizzahub

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import xelagurd.pizzahub.web.controller.HomeController


@WebMvcTest(HomeController::class)
class HomeControllerTest(private val mockMvc: MockMvc) {

    @Test
    @Throws(Exception::class)
    fun testHomePage() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("home"))
            .andExpect(
                MockMvcResultMatchers.content().string(
                    Matchers.containsString("Welcome to...")
                )
            )
    }
}