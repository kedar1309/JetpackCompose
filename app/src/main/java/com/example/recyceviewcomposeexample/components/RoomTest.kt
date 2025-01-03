package com.example.recyceviewcomposeexample.components

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recyceviewcomposeexample.room.Person
import com.example.recyceviewcomposeexample.room.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun RoomTest(viewModel: RoomViewModel, context: Context) {
   // viewModel.addDummy(context)
    val personlist by viewModel.person.collectAsState()

    var personame  by remember {
        mutableStateOf("")
    }
    var age  by remember {
        mutableStateOf("")
    }
    Column (modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), value = personame, onValueChange = {personame = it} , placeholder = {
            Text(text = "Enter name")
        })
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),value = age, onValueChange = {age = it} , placeholder = {
            Text(text = "Enter Age")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),onClick = {
            if(TextUtils.isEmpty(personame) || TextUtils.isEmpty(age)){
                Toast.makeText(context, "Enter name and age", Toast.LENGTH_LONG).show()
                return@Button
            }
            viewModel.addPerson(Person(name = personame.toString(), age = age.toString().toInt()))}) {
            Text(text = "Save person")

        }

        LazyColumn {
            items(personlist){ person ->
                Column (modifier = Modifier.fillMaxWidth().padding(10.dp).background(color = Color.LightGray)){
                    Text(text = "Name ${person.name}", fontSize = 18.sp)
                    Text(text = "Age ${person.age.toString()}" , fontSize = 15.sp)
                }

            }
        }

    }
}

