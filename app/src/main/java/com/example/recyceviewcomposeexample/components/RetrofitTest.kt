package com.example.recyceviewcomposeexample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.recyceviewcomposeexample.retrofit.RetrofitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetrofitTest( viewModel: RetrofitViewModel) {

   val post by  viewModel.post.collectAsState()

   Scaffold (topBar = { TopAppBar(title = { Text(text = "Posts")})}) {
       paddingValues ->

       LazyColumn (modifier = Modifier.padding(top = 16.dp)){
           items(post) {
               post ->
               Card (elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp)) {
                   Column (modifier = Modifier.padding(8.dp)) {
                       Text(text = "Title : ${post.title}", fontStyle = FontStyle.Italic)
                       Spacer(modifier = Modifier.height(8.dp))

                       Text(text = "Body: ${post.body}")
                   }
               }
           }
       }
   }
}