package com.nutrisport.home.domain

import com.nutrisport.shared.Resources
import com.nutrisport.shared.Strings
import com.nutrisport.shared.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class BottomBarDestination(
    val icon: DrawableResource,
    val title: StringResource,
    val screen: Screen
) {
    ProductsOverview(
        icon = Resources.Icon.Home,
        title = Strings.brandName,
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Resources.Icon.ShoppingCart,
        title = Strings.cart,
        screen = Screen.Cart
    ),
    Categories(
        icon = Resources.Icon.Categories,
        title = Strings.cart,
        screen = Screen.Categories
    )
}