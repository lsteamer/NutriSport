package com.nutrisport.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nutrisport.home.domain.BottomBarDestination
import com.nutrisport.shared.IconPrimary
import com.nutrisport.shared.IconSecondary
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onSelect: (BottomBarDestination) -> Unit
){
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 12.dp))
            .padding(
                vertical = 24.dp,
                horizontal = 36.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        BottomBarDestination.entries.forEach { destination ->
            val animatedTint by animateColorAsState(
                targetValue = if(selected) IconSecondary else IconPrimary
            )
            IconButton(onClick = {onSelect(destination)}) {
                Icon(
                    painter = painterResource(destination.icon),
                    contentDescription = "$destination.name Icon",
                    tint = animatedTint
                )
            }
        }
    }
}