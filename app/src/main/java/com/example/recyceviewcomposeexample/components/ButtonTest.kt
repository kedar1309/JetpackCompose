package com.example.recyceviewcomposeexample.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ButtonTest() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Button(modifier = Modifier.padding(10.dp).fillMaxWidth(), onClick = {
            Toast.makeText(context, "buttonclicked", Toast.LENGTH_LONG).show()
        }) {
            Text(text = "Click me")
        }


    }

}