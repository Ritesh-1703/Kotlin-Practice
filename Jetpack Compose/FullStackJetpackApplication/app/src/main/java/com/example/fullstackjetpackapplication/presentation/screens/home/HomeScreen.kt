package com.example.fullstackjetpackapplication.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

data class DashboardItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun HomeScreen() {

    val menuItems = listOf(
        DashboardItem("Profile", Icons.Default.Person),
        DashboardItem("Settings", Icons.Default.Settings),
        DashboardItem("Premium", Icons.Default.Star),
        DashboardItem("Account", Icons.Default.AccountCircle)
    )

    Scaffold {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {

            Text(
                text = "Welcome Back 👋",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Ritesh Dhamale",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Statistics",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                StatCard(
                    title = "Projects",
                    value = "12",
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    title = "Tasks",
                    value = "28",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Quick Access",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(menuItems) { item ->

                    DashboardCard(item)

                }
            }
        }
    }
}

@Composable
fun ProfileCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = "Android Developer",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Jetpack Compose + MVVM",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = title
            )
        }
    }
}

@Composable
fun DashboardCard(
    item: DashboardItem
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.title
            )
        }
    }
}