package com.example.recyceviewcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recyceviewcomposeexample.ui.theme.RecyceViewComposeExampleTheme
import com.example.recyceviewcomposeexample.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CalculatorViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val counterViewModel: CalculatorViewModel by viewModels()

        setContent {
            RecyceViewComposeExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var list = mutableListOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                    RecyleviewDisplay(
                        list = list,
                        sampleviewmodel = counterViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListItem(name : String, viewModel: CalculatorViewModel){
    var total = rememberSaveable {
        mutableStateOf(0)
    }

    Surface (color= Color.LightGray, modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)){
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)) {
            Row  {
                Column (modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)) {
                    Text(text = "Item no "+name)
                    Text(text = "Res: "+total.value, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, fontSize = 15.sp))
                }
                OutlinedButton(onClick = {
                    viewModel.addValue()
                    total.value = total.value+1
                                     },  modifier = Modifier.padding(5.dp)) {
                    Text(text = "+")
                }
                OutlinedButton(onClick = {
                    viewModel.minusValue()
                    if(total.value >0){
                        total.value = total.value-1
                    } } , modifier = Modifier.padding(5.dp)) {
                    Text(text = "-")
                }
            }

        }
    }
}

@Composable
fun RecyleviewDisplay(list: MutableList<String>, modifier: Modifier = Modifier, sampleviewmodel: CalculatorViewModel){

    val counterViewModel = remember { CalculatorViewModel() }
    val viewmodel= CalculatorViewModel ()
    val text by viewmodel.text.observeAsState()
    val sample by viewmodel.onStartLiveData  .observeAsState()
    val sample2 by viewmodel.countdata   .observeAsState()
    Log.d("VIEWMODEL", "Return called ${sample}  and ${sample2}")
    val counterValue = sampleviewmodel.counter.collectAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        LazyColumn(modifier = Modifier
            .padding(vertical = 4.dp)
            .weight(1f)) {
            items(items = list){
                ListItem(name = it, viewModel = viewmodel)
            }

        }
            Text(text = "Total ${counterValue.value}",    modifier = Modifier.padding(10.dp), )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecyceViewComposeExampleTheme {
        var list = mutableListOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        //RecyleviewDisplay(list)
    }
}