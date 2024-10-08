package com.post.presentation.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDialog(onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { // 4
            onClick()
        },
        title = { Text(text = "Error ") },
        text = { Text(text = "Error Loading Info") },
        confirmButton = { // 6
            Button(
                onClick = {
                    onClick()
                }
            ) {
                Text(
                    text = "Ok",
                    color = Color.White
                )
            }
        }
    )
}