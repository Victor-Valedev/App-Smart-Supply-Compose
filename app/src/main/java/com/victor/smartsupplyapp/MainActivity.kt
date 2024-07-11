package com.victor.smartsupplyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victor.smartsupplyapp.ui.theme.Orange
import com.victor.smartsupplyapp.ui.theme.SmartSupplyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSupplyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppSmartSupply()
                }
            }
        }
    }
}

@Composable
fun AppSmartSupply(){

    var valorGasolina by remember { mutableStateOf("") }
    var valorAlcool by remember { mutableStateOf("") }
    var buttonIsClicked by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color(0xF10C373D))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.image_icon),
            contentDescription = null,
            modifier = Modifier
                    .size(300.dp)
                    .offset(y = (-40).dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ÁLCOOL OU GASOLINA?",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TextField(
                value = valorGasolina,
                onValueChange = {
                    valorGasolina = it
                },
                label = {
                    Text(text = "Gasolina (preço por litro):",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp
                        )
                    )
                }
            )
            TextField(
                value = valorAlcool,
                onValueChange = {
                    valorAlcool = it
                },
                label = {
                    Text(text = "Álcool (preço por litro):",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp
                        )
                    )
                }
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(5.dp)
                ) {
                Text(
                    text = "Calcular",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }

}

@Preview
@Composable
fun AppPreview(){
    AppSmartSupply()
}