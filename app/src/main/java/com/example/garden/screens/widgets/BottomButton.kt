package com.example.garden.screens.widgets


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garden.ui.theme.LightGreen
import com.example.garden.ui.theme.White

@Composable
fun BottomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightGreen
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = White,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview
@Composable
private fun Button() {
    //BottomButton("TEST",{})
}