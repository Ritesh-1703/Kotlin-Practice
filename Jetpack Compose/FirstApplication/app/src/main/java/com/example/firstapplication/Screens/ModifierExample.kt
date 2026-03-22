package com.example.firstapplication.Screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ModifierExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chaining Example",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Weight 1",
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.secondary)

            )
            Text(
                "Weight 2",
                modifier = Modifier
                    .weight(2f)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text= "Offset Example",
            modifier = Modifier.offset(10.dp,10.dp)
                .background(MaterialTheme.colorScheme.error)
        )
    }

}

@Composable
@Preview(showBackground = true)
fun PrevEx() {
    ModifierExample()
}
