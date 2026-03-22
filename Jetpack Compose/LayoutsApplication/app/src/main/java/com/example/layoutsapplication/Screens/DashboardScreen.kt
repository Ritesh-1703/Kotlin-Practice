package com.example.layoutsapplication.Screens

import android.R
import android.widget.Button
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //  HEADER (Box + Alignment)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = Color(0xFF6200EE)),
            contentAlignment = Alignment.Center
        ) {
            Text("Dashboard ", color = Color.White, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.height(16.dp))
        //HORIZONTAL LIST (LazyRow)
        Text("Categories", style = MaterialTheme.typography.titleMedium)

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(10) {
                CategoryCard("Item $it")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Grid layout
        Text("Grid Section", style = MaterialTheme.typography.titleMedium)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(6) {
                GridItem("Item $it")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        //Vertical List

        Text("Recent Items", style = MaterialTheme.typography.titleMedium)

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(5) {
                ListItemCard("Item $it")
            }
        }

    }// main Column
}

@Composable
fun CategoryCard(title: String) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(title)
    }
}

@Composable
fun GridItem(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFF03DAC5))
    ) {
        Text(text)
    }
}

@Composable
fun ListItemCard(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEDE7F6))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
        Button(onClick ={}){
            Text("View")
        }
    }

}

@Composable
@Preview(showBackground = true)
fun Prev() {
    DashboardScreen()
}