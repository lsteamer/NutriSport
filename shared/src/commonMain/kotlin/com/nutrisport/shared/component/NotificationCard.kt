package com.nutrisport.shared.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.nutrisport.shared.FontSize

@Composable
fun NotificationCard(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = FontSize.SMALL
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}