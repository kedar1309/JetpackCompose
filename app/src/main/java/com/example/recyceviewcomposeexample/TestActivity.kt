package com.example.recyceviewcomposeexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recyceviewcomposeexample.components.ButtonTest
import com.example.recyceviewcomposeexample.components.CoilTest
import com.example.recyceviewcomposeexample.components.HyperlinkTest
import com.example.recyceviewcomposeexample.components.PasswordTest
import com.example.recyceviewcomposeexample.components.RetrofitTest
import com.example.recyceviewcomposeexample.components.RoomTest
import com.example.recyceviewcomposeexample.components.TextTest
import com.example.recyceviewcomposeexample.retrofit.RetrofitViewModel
import com.example.recyceviewcomposeexample.room.PersonDatabase
import com.example.recyceviewcomposeexample.room.PersonRepository
import com.example.recyceviewcomposeexample.room.RoomViewModel
import com.example.recyceviewcomposeexample.ui.theme.RecyceViewComposeExampleTheme
import com.example.recyceviewcomposeexample.viewmodel.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : ComponentActivity() {

     val componentName = "componentName"


   // private val viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RecyceViewComposeExampleTheme{
                //displayMenu()
                Surface (modifier = Modifier.fillMaxSize(), color = Color.White) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }

            }
        }

    }
    override fun onResume() {
        super.onResume()

    }

    @Composable
    fun NavGraph(navController: NavHostController){
        NavHost(navController = navController, startDestination = ComposeScreen.home.route ){
            composable(route= ComposeScreen.home.route){
                displayMenu(navController)
            }
            composable(route= "Details/{componentName}", arguments = listOf(navArgument(componentName){type= NavType.StringType})){
                Details(name = it.arguments?.getString(componentName))
            }
        }
    }

    @Composable
    fun displayMenu(navController: NavHostController){
        var list = listOfComponent()
        LazyColumn (modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count =  list.size, key =  {
                list[it].toString()
            }, itemContent = { index ->
                val item = list.get(index)
                ComponentRow(item , itemclick = {componentName ->
                    navController.navigate("Details/${componentName}")
                })

            })
        }
    }

    @Composable
    fun ComponentRow(name: String, itemclick: (String) -> Unit){
        Row(modifier = Modifier
            .background(color = Color.LightGray)
            .padding(start = 10.dp, top = 10.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clickable {
                itemclick(name)
            }){
            Text(text = name)
        }
    }

    @Composable
    fun Details(name: String?){
        Log.d("TESTAPP", "name is $name")
        when(name){
            ComponentName.TEXT -> {
                TextTest()
            }
            ComponentName.BUTTON ->{
                ButtonTest()
            }
            ComponentName.COIL ->{
                CoilTest()
            }
            ComponentName.PASSWORD_TEXT ->{
                PasswordTest()
            }
            ComponentName.ROOM ->{

                val userdo = PersonDatabase.getDataBase(LocalContext.current).PersonDAO()
                val noteViewModel = RoomViewModel(userdo)
                RoomTest(noteViewModel, LocalContext.current)
            }
            ComponentName.RETROFIT ->{
                RetrofitTest(RetrofitViewModel())
            }
            ComponentName.HYPERLINK ->{
                HyperlinkTest()
          }

            else ->{
                TextTest()
            }
        }
    }

    private fun listOfComponent() : List<String>{
        var list = mutableListOf<String>()
        list.add(ComponentName.TEXT)
        list.add(ComponentName.BUTTON)
        list.add(ComponentName.COIL)
        list.add(ComponentName.PASSWORD_TEXT)
        list.add(ComponentName.LAZY_COULMN)
        list.add(ComponentName.ROOM)
        list.add(ComponentName.RETROFIT)
        list.add(ComponentName.HYPERLINK)
        return list
    }

}