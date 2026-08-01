package org.carecampuspartner.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carecampuspartner.theming.ErrorRed
import org.carecampuspartner.theming.PrimaryGreen

@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.length < 8 -> 0.2f
        !password.any { it.isDigit() } || !password.any { !it.isLetterOrDigit() } -> 0.6f
        else -> 1.0f
    }

    val color = when {
        strength <= 0.2f -> ErrorRed
        strength <= 0.6f -> Color(0xFFFFA500)
        else -> PrimaryGreen
    }

    val text = when {
        strength <= 0.2f -> "Weak"
        strength <= 0.6f -> "Medium"
        else -> "Strong"
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        LinearProgressIndicator(
            progress = { strength },
            modifier = Modifier.fillMaxWidth().height(4.dp),
            color = color,
            trackColor = color.copy(alpha = 0.2f),
            strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
        )
        Text(
            text = "Password strength: $text",
            fontSize = 11.sp,
            color = color,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = ErrorRed,
        fontSize = 12.sp,
        modifier = Modifier.padding(start = 16.dp, top = 4.dp)
    )
}