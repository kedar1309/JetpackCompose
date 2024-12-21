package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recyceviewcomposeexample.recycle.Person
import com.example.recyceviewcomposeexample.recycle.UserFactory
import com.example.recyceviewcomposeexample.ui.theme.RecyceViewComposeExampleTheme
import com.example.recyceviewcomposeexample.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyCoumnTest(){

    val personRepo = UserFactory().getUserData()
    val stickeylist = listOf("Header 1","Header 2","Header 3","Header 4" )
    LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp)) {
       /* items(personRepo){ person ->PersonRow(person)
        }*/

        stickeylist.forEach{  header ->

            stickyHeader {
                Text(modifier = Modifier.fillMaxWidth().padding(12.dp).background(color = Color.Blue), text = "$header")

            }
            itemsIndexed(personRepo){ index, person ->
                PersonRow(p = person)

            }
        }
        

    }

}

@Composable
fun PersonRow(p: Person){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(color = Color.LightGray) , horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        Column {
            Text(text = p.firstname, style = Typography.bodyLarge)
            Text(text = p.lastName, style = Typography.bodyMedium)
            Text(text = p.age.toString(), style = Typography.bodyMedium)
        }

    }
}

@Preview
@Composable
fun testPersonRow(){
    RecyceViewComposeExampleTheme{
        PersonRow(Person(1, "dfdfd", "ererere", 33))
    }
}