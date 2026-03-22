package com.example.firstapplication.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(name: String, age:Int, country: String){
    Column(){
        TitleText("User Profile")
        Spacer(modifier = Modifier.height(10.dp))

        UserInfo(name,age,country)
        Spacer(modifier = Modifier.height(10.dp))
        ActionButton()
    }
}

@Composable   //Reusable Component
fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable  //Nested Composable
fun UserInfo(name: String, age: Int, country: String) {
    Column() {
        Text(text = "Name: $name")
        Row() {
            Text(text = "Age: $age")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Country: $country")
        }

    }
}

@Composable
fun CustomButton(text: String){
    Button(onClick = {}) {
        Text(text)
    }

}

@Composable  //Multiple Custom Button
fun ActionButton(){
    Row() {
        CustomButton("Edit")
        Spacer(modifier = Modifier.width(10.dp))
        CustomButton("Delete")
    }
}

//Preview
 @Composable
@Preview(showBackground = true)
fun Prev(){
    ProfileScreen("Rohit", 18, "India")
}