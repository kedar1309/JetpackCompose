package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Dropdown (text: String , array: Array<String> , isexpanded : Boolean , onDismiis:() -> Unit, onclick:(Int) -> Unit){
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf(0)
    }


        Row (modifier = Modifier
       .fillMaxWidth()
       .padding(15.dp)
       .clickable { isDropDownExpanded.value = true } , ) {

       Text(text = array[itemPosition.value], fontSize = 16.sp)
       DropdownMenu(expanded =isDropDownExpanded.value , onDismissRequest = { isDropDownExpanded.value = false }) {

           array.forEachIndexed { index, username ->
               DropdownMenuItem(text = {
                   Text(text = username)
               },
                   onClick = {
                       isDropDownExpanded.value = false
                        itemPosition.value = index
                       onclick(index)
                   })
           }

           }

       }

   }


