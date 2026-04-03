//package com.example.uicomponents.Screens
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.icons.filled.Settings
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MaterialComponents(){
//    var selectedItem by remember { mutableStateOf(0) }
//
//    val items = listOf("Home", "Profile", "Settings")
//    val icons = listOf(Icons.Default.Home, Icons.Default.Person, Icons.Default.Settings)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(items [selectedItem]) },
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Default.Menu, contentDescription = "Menue")
//                    }
//                },
//                actions = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Default.Search, contentDescription = "Search")
//                    }
//                }
//            )
//        },
//
//        bottomBar = {
//            NavigationBar {
//                items.forEachIndexed { index, item ->
//                    NavigationBarItem(
//                       selected =  selectedItem == index,
//                        onClick = { selectedItem = index},
//                        icon ={
//                            Icon(icons[index], contentDescription = item)
//                        },
//                        label = {Text(item)}
//                    )
//                }
//            }
//        }
//    ){
//        padding ->
//        when (selectedItem){
////            0 -> HomeScreen(Modifier.padding(padding))
//            0-> ProfileScreen(Modifier.padding(padding))
//            1 -> SettingsScreen(Modifier.padding(padding))
//        }
//    }
//
//
//}
//
////@Composable
////fun HomeScreen(modifier: Modifier) {
////    Text("Home Screen", modifier = modifier)
////}
//
//@Composable
//fun ProfileScreen(modifier: String?) {
//    Text("Profile Screen", modifier = modifier)
//}
//
//@Composable
//fun SettingsScreen(modifier: Modifier) {
//    Text("Settings Screen", modifier = modifier)
//}