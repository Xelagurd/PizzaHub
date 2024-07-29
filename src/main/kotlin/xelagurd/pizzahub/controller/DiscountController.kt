package xelagurd.pizzahub.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import xelagurd.pizzahub.controller.utils.DiscountCodeProps

@Controller
@RequestMapping("/discounts")
class DiscountController(private val discountProps: DiscountCodeProps) {

    @GetMapping
    fun displayDiscountCodes(model: Model): String {
        val codes = discountProps.codes
        model.addAttribute("codes", codes)

        return "discountList"
    }
}