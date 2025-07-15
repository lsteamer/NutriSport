package com.nutrisport.shared.domain

import androidx.compose.ui.graphics.Color
import com.nutrisport.shared.CategoryYellow
import kotlinx.serialization.Serializable
import nutrisport.shared.generated.resources.Res
import nutrisport.shared.generated.resources.accessories
import nutrisport.shared.generated.resources.creatine
import nutrisport.shared.generated.resources.grainers
import nutrisport.shared.generated.resources.preworkout
import nutrisport.shared.generated.resources.protein
import org.jetbrains.compose.resources.StringResource

@Serializable
data class Product(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val category: String,
    val flavors: List<String>? = null,
    val weight: Int? = null,
    val price: Double,
    val isPopular: Boolean = false,
    val isDiscounted: Boolean = false,
    val isNew: Boolean = false
)

enum class ProductCategory(
    val title: StringResource,
    val color: Color
) {
    Protein(
        title = Res.string.protein,
        color = CategoryYellow
    ),
    Creatine(
        title = Res.string.creatine,
        color = CategoryYellow
    ),
    PreWorkout(
        title = Res.string.preworkout,
        color = CategoryYellow
    ),
    Grainers(
        title = Res.string.grainers,
        color = CategoryYellow
    ),
    Accessories(
        title = Res.string.accessories,
        color = CategoryYellow
    ),
}