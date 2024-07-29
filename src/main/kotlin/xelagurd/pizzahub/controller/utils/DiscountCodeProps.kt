package xelagurd.pizzahub.controller.utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "pizza.discount")
class DiscountCodeProps(
    var codes: Map<String, Int> = HashMap()
)