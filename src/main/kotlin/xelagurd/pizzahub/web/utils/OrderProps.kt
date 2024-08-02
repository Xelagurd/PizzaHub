package xelagurd.pizzahub.web.utils

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated

@Component
@ConfigurationProperties(prefix = "pizza.orders")
@Validated
class OrderProps(
    @field:Min(value = 5, message = "Must be between 5 and 25")
    @field:Max(value = 25, message = "Must be between 5 and 25")
    var pageSize: Int = 20
)