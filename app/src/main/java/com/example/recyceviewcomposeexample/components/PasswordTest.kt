package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.recyceviewcomposeexample.R


@Composable
fun PasswordTest() {

    var password by rememberSaveable {
        mutableStateOf("")
    }
    var username by rememberSaveable {
        mutableStateOf("")
    }

    var passwordstate by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordstate)
        painterResource(id = R.drawable.ic_launcher_background)
    else
        painterResource(id = R.drawable.error)

    Column(
        modifier = Modifier
            .fillMaxSize()
            , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {



        OutlinedTextField(modifier = Modifier.padding(top = 20.dp), value = username,
            onValueChange = { username = it }, placeholder = {
                Text(text = "Username", style = TextStyle(color = Color.Blue))

            },
            label = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        OutlinedTextField(modifier = Modifier.padding(top = 20.dp) ,value = password,
            onValueChange = { password = it }, placeholder = {
                Text(text = "Password", style = TextStyle(color = Color.Blue))

            },
            label = { Text(text = "Password") },
            trailingIcon = {
                IconButton(onClick = { passwordstate = !passwordstate }) {
                    Icon(painter = icon, contentDescription = "Password visibility")

                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordstate) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        
        Button(
            modifier = Modifier.padding(top = 15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { }) {
            Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).width(100.dp).height(50.dp).background(Brush.horizontalGradient(
                listOf(Color.LightGray, Color.Blue)
            )), contentAlignment = Alignment.Center){
                Text(text = "Click me")
            }
        }

    }
}