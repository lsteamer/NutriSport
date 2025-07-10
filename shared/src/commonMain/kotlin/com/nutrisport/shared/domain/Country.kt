package com.nutrisport.shared.domain

import com.nutrisport.shared.Resources
import org.jetbrains.compose.resources.DrawableResource

enum class Country(
    val dialCode: Int,
    val code: String,
    val flag: DrawableResource
) {
    Mexico(
        dialCode = 52,
        code = "MX",
        flag = Resources.Flag.Mexico
    ),
    Germany(
        dialCode = 49,
        code = "DE",
        flag = Resources.Flag.Germany
    ),
    USA(
        dialCode = 1,
        code = "US",
        flag = Resources.Flag.USA
    )
}