package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextTest() {


    var fontArray = arrayOf(
        FontStyle.Normal.toString(),
        FontStyle.Italic.toString()
    )
    var fontvalue by remember { mutableStateOf(FontStyle.Normal) }


    var textselectAttay = arrayOf("Selectable" , "Non selectable")
    var selected by remember {
        mutableStateOf("Selectable")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        if(selected.equals("Selectable")){
            SelectionContainer {
                Text(text = "Hellow world".repeat(10), fontStyle = fontvalue, fontSize = 18.sp,  )
            }
        }else{
            DisableSelection {
                Text(text = "Hellow world".repeat(10), fontStyle = fontvalue, fontSize = 18.sp,  )
            }
        }

        //Text(text = "Hellow world".repeat(10), fontStyle = fontvalue, fontSize = 18.sp,  )

        Dropdown(text = "select font", fontArray, isexpanded = false, { }, { index ->
            if (index == 0) {
                fontvalue = FontStyle.Normal
            } else {
                fontvalue = FontStyle.Italic
            }
        })

        Dropdown(text = "select Seletable or not", textselectAttay, isexpanded = false, { }, { index ->
            if (index == 0) {
                selected = "Selectable"
            } else {
                selected = "Non selectable"
            }
        })
    }

}