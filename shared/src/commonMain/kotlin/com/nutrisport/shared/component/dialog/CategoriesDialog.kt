package com.nutrisport.shared.component.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nutrisport.shared.Alpha
import com.nutrisport.shared.FontSize
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.Resources
import com.nutrisport.shared.Strings
import com.nutrisport.shared.Surface
import com.nutrisport.shared.TextPrimary
import com.nutrisport.shared.TextSecondary
import com.nutrisport.shared.domain.ProductCategory
import nutrisport.shared.generated.resources.Res
import nutrisport.shared.generated.resources.cancel
import nutrisport.shared.generated.resources.checkmark_icon
import nutrisport.shared.generated.resources.confirm
import nutrisport.shared.generated.resources.pick_country
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CategoriesDialog(
    category: ProductCategory,
    onDismiss: () -> Unit,
    onConfirm: (ProductCategory) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(category) }

    AlertDialog(
        containerColor = Surface,
        title = {
            Text(
                text = stringResource(Res.string.pick_country),
                fontSize = FontSize.EXTRA_MEDIUM,
                color = TextPrimary
            )
        },
        text = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                ProductCategory.entries.forEach { currentCategory ->
                    val animatedBackground by animateColorAsState(
                        targetValue = if (currentCategory == selectedCategory)
                            currentCategory.color.copy(alpha = Alpha.TWENTY_PERCENT)
                        else Color.Transparent
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(size = 6.dp))
                            .background(
                                animatedBackground
                            )
                            .clickable { selectedCategory = currentCategory }
                            .padding(vertical = 16.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = stringResource(currentCategory.title),
                            modifier = Modifier.weight(1f),
                            color = TextPrimary,
                            fontSize = FontSize.REGULAR
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        AnimatedVisibility(visible = selectedCategory == currentCategory) {
                            Icon(
                                painter = painterResource(Resources.Icon.Checkmark),
                                contentDescription = stringResource(Res.string.checkmark_icon),
                                tint = IconPrimary
                            )
                        }
                    }

                }
            }

        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { onConfirm(selectedCategory) },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = TextSecondary
                )
            ) {
                Text(
                    text = stringResource(Res.string.confirm),
                    fontSize = FontSize.REGULAR,
                    fontWeight = FontWeight.Medium
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = TextPrimary.copy(alpha = Alpha.HALF)
                )
            ) {
                Text(
                    text = stringResource(Res.string.cancel),
                    fontSize = FontSize.REGULAR,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}